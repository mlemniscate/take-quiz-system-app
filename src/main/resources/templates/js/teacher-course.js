let course;

// call function load page first
ajaxGetCourseById();
showCourse();
showQuizzes();

// Listeners
// go back to previous page button
$('#backItem').click((event) => {
  window.location.href = 'teacher-page.html';
});

// save quiz button click
$('#saveAddedQuiz').click((event) => {
  let title = $('#titleInput').val();
  let description = $('#descriptionInput').val();
  let time = $('#timeInput').val();
  let id = $('#idInput').val();
  let quiz = { id, title, description, time };
  if (title != '' && description != '' && time != '') {
    ajaxAddQuizToCourse(quiz);
  } else {
    alert('لطفا همه ی فیلد ها را پر کنید!');
  }
});

// put modal info for editting quiz
$('#addQuizModal').on('show.bs.modal', function (e) {
  let quiz = $(e.relatedTarget).data('quiz');
  if (quiz != null) {
    $('#titleInput').val(quiz.title);
    $('#descriptionInput').val(quiz.description);
    $('#timeInput').val(quiz.time);
    $('#idInput').val(quiz.id);
  } else {
    $('#titleInput').val('');
    $('#descriptionInput').val('');
    $('#timeInput').val('');
    $('#id').val(null);
  }
});

// Ajax
// get course and show it
// ajax for get teacher
function ajaxGetCourseById() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/${sessionStorage.getItem('courseId')}`,
    async: false,
    success: function (response) {
      course = response;
    },
  });
}

// ajax for save and add quiz to course
function ajaxAddQuizToCourse(quiz) {
  $.ajax({
    type: 'POST',
    url: `http://localhost:8080/course/add-quiz/${course.id}`,
    contentType: 'application/json',
    data: JSON.stringify(quiz),
    async: false,
    success: function (response) {
      course = response;
      location.reload();
    },
  });
}

// ajax for delete a quiz
function deleteQuiz(id) {
  if (confirm('آیا از حذف این گزنیه مطمئنید؟')) {
    $.ajax({
      type: 'DELETE',
      url: `http://localhost:8080/quiz/${id}`,
      async: false,
      success: function (response) {
        location.reload();
      },
    });
  }
}

// Show
// show course in page
function showCourse() {
  $('#title').html(course.title);
  $('#dateStart').html(course.startDate);
  $('#dateEnd').html(course.endDate);
}

// show quizzes
function showQuizzes() {
  $('#quizContainer').html('');
  for (let quizIndex = 0; quizIndex < course.quizzes.length; quizIndex++) {
    const quiz = course.quizzes[quizIndex];
    $('#quizContainer').append(getQuizHTML(quiz));
  }
}

// go to quiz questions
function showQuizQuestions(quiz) {
  sessionStorage.setItem('quiz', JSON.stringify(quiz));
  window.location.href = 'teacher-question.html';
}

// Get HTML
function getQuizHTML(quiz) {
  return `<div class="card mb-4" style="width: 18rem; justify-self: center">
  <div class="card-body">
    <div class="card-title d-flex">
      <div class="w-100">
        <h5 class="mx-3 h5">${quiz.title}</h5>
      </div>
    </div>
    <div class="d-flex">
      <p class="">
        <b>زمان آزمون: &nbsp;</b>
      </p>
      <p>${quiz.time} دقیقه</p>
    </div>
    <div class="d-flex height-10">
      <p class="">
        <b>توضیحات:</b>&nbsp; <span>${quiz.description}</span>
      </p>
    </div>
    <div class="d-flex justify-content-between">
      <button class="btn btn-info w-50 m-1" 
      data-quiz='${JSON.stringify(quiz)}'
      data-bs-toggle="modal"
      data-bs-target="#addQuizModal">
        <i class="fas fa-edit"></i> ویرایش
      </button>
      <button onclick='deleteQuiz(${quiz.id})'
        class="btn btn-danger w-50 m-1">
        <i class="fa fa-minus" aria-hidden="true"></i> حذف
      </button>
    </div>
    <button 
      class="btn btn-primary open-edit-user-modal my-3 w-100"
      onclick='showQuizQuestions(${JSON.stringify(quiz)})'
    >
      <i class="fas fa-edit"></i> ویرایش سوالات آزمون
    </button>
  </div>
</div>`;
}
