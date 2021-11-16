let quiz = JSON.parse(sessionStorage.getItem('quiz'));
let multiQuestions;
let descriptiveQuestions;
let optionNumberCounter = 3;
// function for the first time page loaded
showQuiz();
ajaxGetQuestion();
showQuestions();

// listener for events
// multi question adding button
$('#multiQuestionAddButton').click((event) => {
  $('#multiQuestionIdInput').val(null);
  $('#multiQuestionAddBar').removeClass('d-none');
  $('#descQuestionAddBar').addClass('d-none');
  $('#addQuestionTypeModal').modal('hide');
});
// descriptoin question adding button
$('#descQuestionAddButton').click((event) => {
  $('#descQuestionIdInput').val(null);
  $('#descQuestionAddBar').removeClass('d-none');
  $('#multiQuestionAddBar').addClass('d-none');
  $('#addQuestionTypeModal').modal('hide');
});

// adding question cancel button
$('.cancel-add-question').click((event) => {
  $('#descQuestionAddBar').addClass('d-none');
  $('#multiQuestionAddBar').addClass('d-none');
});

// adding option to question button
$('#addOptionButton').click((event) => {
  $('#optionsContainer').append(getOptionHTML(optionNumberCounter++));
});

// save multi option question and add to quiz questions
$('#multiQuestionSaveButton').click((event) => {
  let optionElemens = $('#optionsContainer :input[type=text]');
  let options = [];
  let fullFields = true;
  for (let index = 0; index < optionElemens.length; index++) {
    const element = optionElemens[index];
    if ($(element).val() == '') {
      alert('همه‌ی فیلدها را پر کنید.');
      fullFields = false;
      break;
    }
    options.push($(element).val());
  }
  let question = {
    id: $('#multiQuestionIdInput').val(),
    title: $('#multiQuestionTitleInput').val(),
    question: $('#multiQuestionTextInput').val(),
    score: $('#multiQuestionScoreInput').val(),
    trueOption: $('input:radio[name=multiQuestionOptionRadio]:checked').val(),
    options,
  };
  if (
    question.title == '' ||
    question.question == '' ||
    question.score == '' ||
    question.trueOption == ''
  ) {
    alert('همه‌ی فیلدها را پر کنید.');
    fullFields = false;
  }
  if (fullFields) {
    ajaxSaveQuestion(question, 'multi-choice-question');
  }
});

// save desc question and add to quiz questions
$('#descQuestionSaveButton').click((event) => {
  let question = {
    id: $('#descQuestionIdInput').val(),
    title: $('#descQuestionTitleInput').val(),
    question: $('#descQuestionTextInput').val(),
    score: $('#descQuestionScoreInput').val(),
  };
  if (question.title == '' || question.question == '' || question.score == '') {
    alert('همه‌ی فیلدها را پر کنید.');
  } else {
    ajaxSaveQuestion(question, 'descriptive-question');
  }
});

// edit button of multi questions
function editQuestionClick(element) {
  let question = $(element).data('question');

  if (question.hasOwnProperty('options')) {
    $('#multiQuestionAddButton').click();
    window.scrollTo(0, 0);
    $('#multiQuestionIdInput').val(question.id);
    $('#multiQuestionTitleInput').val(question.title);
    $('#multiQuestionTextInput').val(question.question);
    $('#multiQuestionScoreInput').val(question.score);
    let radios = $('input:radio[name=multiQuestionOptionRadio]');
    for (let i = 2; i < radios.length; i++) {
      $(radios[i]).parent().remove();
      optionNumberCounter--;
    }
    for (let i = 2; i < question.options.length; i++) {
      $('#addOptionButton').click();
    }
    let optionElemens = $('#optionsContainer :input[type=text]');
    for (let index = 0; index < optionElemens.length; index++) {
      const element = optionElemens[index];
      $(element).val(question.options[index]);
    }
    $('input:radio[name=multiQuestionOptionRadio]')[
      question.trueOption - 1
    ].checked = true;
  } else {
    $('#descQuestionAddButton').click();
    window.scrollTo(0, 0);
    $('#descQuestionIdInput').val(question.id);
    $('#descQuestionTitleInput').val(question.title);
    $('#descQuestionTextInput').val(question.question);
    $('#descQuestionScoreInput').val(question.score);
  }
}

// delete option button
function deleteOptionButton(button) {
  $(button).parent().remove();
  optionNumberCounter--;
}

// Ajax
// get course and show it
// ajax for get teacher
function ajaxGetQuestion() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/multi-choice-question/get-questions/${quiz.id}`,
    async: false,
    success: function (response) {
      multiQuestions = response;
      console.log(multiQuestions);
    },
  });
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/descriptive-question/get-questions/${quiz.id}`,
    async: false,
    success: function (response) {
      descriptiveQuestions = response;
      console.log(descriptiveQuestions);
    },
  });
}

function ajaxSaveQuestion(question, questionType) {
  $.ajax({
    type: 'POST',
    url: `http://localhost:8080/${questionType}/
    ${sessionStorage.getItem('courseId')}/
    ${sessionStorage.getItem('teacherId')}/
    ${quiz.id}`,
    contentType: 'application/json',
    data: JSON.stringify(question),
    async: false,
    success: function (response) {
      location.reload();
    },
  });
}

// Show
// show quiz in page
function showQuiz() {
  $('#title').html(quiz.title);
  $('#time').html(quiz.time);
}

// show all the questions that a quiz have
function showQuestions() {
  $('#questionContainer').html('');
  for (let index = 0; index < multiQuestions.length; index++) {
    const question = multiQuestions[index];
    $('#questionContainer').append(getMultiCardHTML(question));
    // make options html
    for (
      let indexOption = 0;
      indexOption < question.options.length;
      indexOption++
    ) {
      const option = question.options[indexOption];
      let checked = question.trueOption == indexOption + 1 ? 'checked' : '';
      $(`#optionContainer${question.id}`).append(
        getQuestionOptionHTML(option, question, indexOption + 1, checked)
      );
    }
  }
  for (let index = 0; index < descriptiveQuestions.length; index++) {
    const question = descriptiveQuestions[index];
    $('#questionContainer').append(getDescQuestionHTML(question));
  }
}

//HTML
// Multi questions HTML
function getMultiCardHTML(question) {
  return `<div id="multiQuestionCard" class="card mb-4">
  <div class="card-body">
    <div class="card-title d-flex justify-content-between">
      <div class="w-100">
        <p class="mx-3 h4">${question.title}</p>
      </div>
      <div>
        <p class="mx-3">${question.score}</p>
      </div>
    </div>
    <div class="d-flex">
      <p class="">${question.question}</p>
    </div>
    <div id="optionContainer${question.id}">
    </div>
    <div class="d-flex justify-content-between w-25 mt-4">
      <button
        onclick="editQuestionClick(this)"
        class="btn btn-info w-50 m-1"
        data-question='${JSON.stringify(question)}'
      >
        <i class="fas fa-edit"></i> ویرایش
      </button>
      <button
        onclick="deleteQuestion('${question.id}')"
        class="btn btn-danger w-50 m-1"
      >
        <i class="fa fa-minus" aria-hidden="true"></i> حذف
      </button>
    </div>
  </div>
</div>`;
}

// Get option HTML
function getQuestionOptionHTML(option, question, number, checked) {
  return `<li class="list-group-item d-flex">
  <input
    id="teacherInputRadio"
    class="form-check-input me-1"
    type="radio"
    value=${number}
    name="${question.id}"
    disabled
    ${checked}
  />
  <label class="w-100 h-100">
    ${option}</label
  >
</li>`;
}

function getDescQuestionHTML(question) {
  return `<div id="descQuestionCard" class="card mb-4">
  <div class="card-body">
    <div class="card-title d-flex justify-content-between">
      <div class="w-100">
        <p class="mx-3 h4">${question.title}</p>
      </div>
      <div>
        <p class="mx-3">${question.score}</p>
      </div>
    </div>
    <div class="d-flex">
      <p class="">${question.question}</p>
    </div>
    <div class="d-flex justify-content-between w-25 mt-4">
      <button
        onclick="editQuestionClick(this)"
        class="btn btn-info w-50 m-1"
        data-question='${JSON.stringify(question)}'
      >
        <i class="fas fa-edit"></i> ویرایش
      </button>
      <button
        onclick="deleteQuestion('${question.id}')"
        class="btn btn-danger w-50 m-1"
      >
        <i class="fa fa-minus" aria-hidden="true"></i> حذف
      </button>
    </div>
  </div>
</div>`;
}

function getOptionHTML(number) {
  return `<div class="d-flex align-items-center my-1">
  <input type="radio" name="multiQuestionOptionRadio" value='${number}' class="form-check-input">
  <input type="text" class="form-control" id="questionOptionInput">
  <button onclick="deleteOptionButton(this)" class="btn btn-danger"><i class="fas fa-minus    "></i></button>
</div>`;
}
