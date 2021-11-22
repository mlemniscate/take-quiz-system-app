let quiz = JSON.parse(sessionStorage.getItem('quiz'));
let startDate = JSON.parse(sessionStorage.getItem('startDate'));
let student = JSON.parse(sessionStorage.getItem('student'));
let multiQuestions;
let descQuestions;
let questions = [];
let answers = [];

let questionNumber = 0;

// first time run
// getting questions
ajaxGetQuestion();
checkAndStartTimer();
showQuestion();

// Listeners
// Next question click
$('#nextQuestion').click((event) => {
  if (questionNumber < questions.length - 1) {
    // take prev question answer
    let answer = answers[questionNumber];
    if (questions[questionNumber].hasOwnProperty('options'))
      answer.answer = $('input[name="multiQuestionOptionRadio"]:checked').val();
    else answer.answer = $('#descAnswer');

    questionNumber++;
    showQuestion();
    if (questionNumber == questions.length - 1)
      $('#finishQuiz').removeClass('d-none');
  }
});

// Prev question click
$('#prevQuestion').click((event) => {
  $('#finishQuiz').addClass('d-none');
  if (questionNumber > 0) {
    questionNumber--;
    showQuestion();
  }
});

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
        console.log('end');
        clearInterval(x);
      }
    }, 1000);
  } else {
    if (new Date().getTime() - startDate < quiz.time * 60 * 1000) {
      time = quiz.time * 60 * 1000 - (new Date().getTime() - startDate);
      var x = setInterval(function () {
        time -= 1000;
        var minutes = Math.floor((time % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((time % (1000 * 60)) / 1000);
        $('#time').html(minutes + ':' + seconds);
        // If the count down is over, write some text
        if (time <= 0) {
          console.log('end');
          clearInterval(x);
        }
      }, 1000);
    } else {
      // implement the end point
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
    let answer = { answer: undefined, questionsId: questions[i].id };
    answers.push(answer);
  }
  console.log(answers);
}

// Ajax for getting started quiz and get the date of quiz started
// /quiz/start-student-quiz/{studentId}/{quizId}
function ajaxQuizStart() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/quiz/start-student-quiz/${student.id}/${quiz.id}`,
  });
}

// HTML
function getOptionHTML(number, option, checkedVal) {
  return `<div class="d-flex align-items-center my-1">
  <input id="option${number}" type="radio" name="multiQuestionOptionRadio" value='${number}' class="form-check-input" ${checkedVal}>
  <label for="option${number}" type="text" class="form-control" id="questionOptionInput">${option}</label>
</div>`;
}
