let quiz = JSON.parse(sessionStorage.getItem('quiz'));
let startDate = JSON.parse(sessionStorage.getItem('startDate'));
let student = JSON.parse(sessionStorage.getItem('student'));
let studentQuiz = JSON.parse(sessionStorage.getItem('studentQuiz'));
studentQuiz.isActive = false;
let multiQuestions;
let descQuestions;
let questions = [];
let answers = [];

let questionNumber = 0;

// first time run
// getting questions
ajaxGetQuestion();
ajaxGetAnswers();
checkAndStartTimer();
showQuestion();

// Listeners
// Next question click
$('#nextQuestion').click((event) => {
  if (questionNumber < questions.length - 1) {
    // take prev question answer
    saveAnswer();
    questionNumber++;
    showQuestion();
    if (questionNumber == questions.length - 1)
      $('#finishQuiz').removeClass('d-none');
  }
  console.log(answers);
});

// Prev question click
$('#prevQuestion').click((event) => {
  $('#finishQuiz').addClass('d-none');
  if (questionNumber > 0) {
    questionNumber--;
    showQuestion();
  }
  console.log(answers);
});

// Finish quiz button listener
$('#finishQuiz').click((event) => {
  finishQuiz(
    'آزمون شما با موفقیت پایان یافته و ثبت شد. منتظر اصلاح و نمرات باشید. از صبر و شکیبایی شما متشکریم.'
  );
});

$('#backItem').click((event) => {
  window.location.href = 'student-course.html';
});

// Finish quiz
function finishQuiz(endQuizMessage) {
  saveAnswer();
  ajaxToEndQuiz();
  sessionStorage.setItem('endQuizMessage', endQuizMessage);
  window.location.href = 'student-finish-quiz.html';
}

function saveAnswer() {
  let answer = answers[questionNumber];
  let question = questions[questionNumber];
  console.log(answer);
  console.log(question);
  if (question.hasOwnProperty('options'))
    answer.answer = $('input[name="multiQuestionOptionRadio"]:checked').val();
  else answer.answer = $('#descAnswer').val();
  console.log(answer);
  ajaxSaveAnswer(answer, question);
}

// Show
// Show question
function showQuestion() {
  let question = questions[questionNumber];
  $('#questionText').html(question.question);
  $('#score').html(question.score);
  if (question.hasOwnProperty('options')) {
    $('#option').html('');
    $('#descAnswer').addClass('d-none');
    let answer = answers[questionNumber].answer;
    let checkedIndex = answer != undefined ? answer : -1;
    for (let i = 0; i < question.options.length; i++) {
      const option = question.options[i];
      let checkedVal = checkedIndex == i + 1 ? 'checked' : '';
      $('#option').append(getOptionHTML(i + 1, option, checkedVal));
    }
  } else {
    $('#option').html('');
    $('#descAnswer').removeClass('d-none');
    let answer = answers[questionNumber].answer;
    $('#descAnswer').html(answer != undefined ? answer : '');
  }
}

function checkAndStartTimer() {
  // if student start the quiz
  if (startDate == null) {
    ajaxQuizStart();
    time = quiz.time * 60 * 1000;
    var x = setInterval(function () {
      time -= 1000;
      var minutes = Math.floor((time % (1000 * 60 * 60)) / (1000 * 60));
      var seconds = Math.floor((time % (1000 * 60)) / 1000);
      $('#time').html(minutes + ':' + seconds);
      // If the count down is over, write some text
      if (time <= 0) {
        finishQuiz(
          'زمان آزمون شما پایان یافت و ثبت شد. منتظر اصلاح و نمرات باشید. از صبر و شکیبایی شما متشکریم.'
        );
        clearInterval(x);
      }
    }, 1000);
  }
  // if student before stated the quiz and keep going
  else {
    if (new Date().getTime() - startDate < quiz.time * 60 * 1000) {
      time = quiz.time * 60 * 1000 - (new Date().getTime() - startDate);
      var x = setInterval(function () {
        time -= 1000;
        var minutes = Math.floor((time % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((time % (1000 * 60)) / 1000);
        $('#time').html(minutes + ':' + seconds);
        // If the count down is over, write some text
        if (time <= 0) {
          finishQuiz(
            'زمان آزمون شما پایان یافت و ثبت شد. منتظر اصلاح و نمرات باشید. از صبر و شکیبایی شما متشکریم.'
          );
          clearInterval(x);
        }
      }, 1000);
    } else {
      finishQuiz(
        'زمان آزمون شما پایان یافت و ثبت شد. منتظر اصلاح و نمرات باشید. از صبر و شکیبایی شما متشکریم.'
      );
    }
  }
}

// Ajax
// Ajax for getting quiz questions
function ajaxGetQuestion() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/multi-choice-question/get-questions/${quiz.id}`,
    async: false,
    success: function (response) {
      multiQuestions = response;
      questions.push(...multiQuestions);
    },
  });
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/descriptive-question/get-questions/${quiz.id}`,
    async: false,
    success: function (response) {
      descQuestions = response;
      questions.push(...descQuestions);
    },
  });
  for (let i = 0; i < questions.length; i++) {
    let answer = { answer: undefined };
    answers.push(answer);
  }
}

// Ajax for getting started quiz and get the date of quiz started
function ajaxQuizStart() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/quiz/start-student-quiz/${student.id}/${quiz.id}`,
  });
}

// Ajax for updating or saving answer
function ajaxSaveAnswer(answer, question) {
  $.ajax({
    type: 'POST',
    url: `http://localhost:8080/answer/${question.id}/${student.id}/${quiz.id}`,
    contentType: 'application/json',
    data: JSON.stringify(answer),
    async: false,
    success: function (response) {
      answers[questionNumber] = response;
    },
  });
}

// Ajax for getting answered questions
function ajaxGetAnswers() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/answer/${student.id}/${quiz.id}`,
    async: false,
    success: function (response) {
      for (let i = 0; i < response.length; i++) {
        const answer = response[i];
        answers[i] = answer;
      }
    },
  });
}

// Ajax for finish quiz and set isActive false
function ajaxToEndQuiz() {
  $.ajax({
    type: 'PUT',
    url: `http://localhost:8080/quiz/student-quiz-finish`,
    contentType: 'application/json',
    data: JSON.stringify(studentQuiz),
    async: false,
    success: function (response) {
      answers[questionNumber] = response;
    },
  });
}

// HTML
function getOptionHTML(number, option, checkedVal) {
  return `<div class="d-flex align-items-center my-1">
  <input id="option${number}" type="radio" name="multiQuestionOptionRadio" value='${number}' class="form-check-input" ${checkedVal}>
  <label for="option${number}" type="text" class="form-control" id="questionOptionInput">${option}</label>
</div>`;
}
