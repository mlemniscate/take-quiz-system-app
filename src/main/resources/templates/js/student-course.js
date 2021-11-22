let course = JSON.parse(sessionStorage.getItem('course'));
let student = JSON.parse(sessionStorage.getItem('student'));
let studentQuizzes;
// call function load page first
ajaxGetStudentQuiz();
console.log(studentQuizzes);
showCourse();
showQuizzes();

// Listeners
// go back to previous page button
$('#backItem').click((event) => {
  window.location.href = 'student-page.html';
});

// Ajax
// ajax for get studentQuizzes
function ajaxGetStudentQuiz(url) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/quiz/all-student-quiz/${student.id}`,
    async: false,
    success: function (response) {
      studentQuizzes = response;
    },
  });
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
  for (let quizIndex = 0; quizIndex < studentQuizzes.length; quizIndex++) {
    const studentQuiz = studentQuizzes[quizIndex];
    $('#quizContainer').append(
      getQuizHTML(
        studentQuiz.quiz,
        studentQuiz.isActive,
        studentQuiz.score,
        studentQuiz.startDate
      )
    );
  }
}

// go to quiz questions
function startQuiz(obj) {
  sessionStorage.setItem('quiz', JSON.stringify(obj.quiz));
  sessionStorage.setItem('startDate', JSON.stringify(obj.startDate));
  window.location.href = 'student-quiz.html';
}

// Get HTML
function getQuizHTML(quiz, isActive, score, startDate) {
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
    <div class="d-flex height-6">
      <p class="">
        <b>توضیحات:</b>&nbsp; <span>${quiz.description}</span>
      </p>
    </div>
    <button 
      class="btn btn-primary open-edit-user-modal my-3 w-100"
      onclick='startQuiz(${JSON.stringify({ quiz, startDate })})'
      ${isActive ? '' : 'disabled'}
    >
      <i class="fas fa-edit"></i> شروع آزمون
    </button>
    ${
      isActive
        ? ''
        : `<p>نمره آزمون : ${score == null ? 'اصلاح نشده' : score}</p>`
    }
  </div>
</div>`;
}
