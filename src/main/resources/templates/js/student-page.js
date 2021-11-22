let courses;
let student;

// -------------------------------------------------------------------------------------
// Page loads
// Student loads
ajaxGetStudentByUsername();

// Course loads
ajaxGetCoursesAndShow();

// Show student name
$('#studentFullName').html(`${student.firstName} ${student.lastName}`);

// -------------------------------------------------------------------------------------
// functions for Navbar
$('#logoutItem').click((event) => {
  if (confirm('آیا می خواهید از حساب خود خارج شوید؟')) {
    sessionStorage.clear();
    window.location.href = 'index.html';
  }
});

// course details click event
function showCourseDetails(course) {
  console.log(course);
  sessionStorage.setItem('course', JSON.stringify(course));
  sessionStorage.setItem('student', JSON.stringify(student));
  window.location.href = 'student-course.html';
}

// ajax for get student
function ajaxGetStudentByUsername() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/student/get-by-username?username=${sessionStorage.getItem(
      'username'
    )}`,
    async: false,
    success: function (response) {
      student = response;
      console.log(student);
    },
  });
}

// ajax for get courses and show them
function ajaxGetCoursesAndShow(url) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/all-student-courses/${student.id}`,
    async: false,
    success: function (response) {
      courses = response;
      $('#courseContainer').html('');
      for (let courseIndex = 0; courseIndex < response.length; courseIndex++) {
        const element = response[courseIndex];
        let html = getShowCourseHTML(element);
        $('#courseContainer').append(html);
      }
    },
  });
}

// -----------------------------------------------------------------------------------------
// Get HTML Strings
// Get Course HTML
function getShowCourseHTML(course) {
  return `<div class="card mb-4" style="width: 18rem; justify-self: center">
  <div class="card-body">
    <div class="card-title d-flex align-items-center">
      <div class="w-100 text-center">
        <h5 class="mx-3 h5">${course.title}</h5>
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <p class="">
        تاریخ شروع <i class="fas fa-long-arrow-alt-left mx-3"></i>
      </p>
      <p>${course.startDate}</p>
    </div>
    <div class="d-flex justify-content-center">
      <p class="">
        تاریخ پایان <i class="fas fa-long-arrow-alt-left mx-3"></i>
      </p>
      <p>${course.endDate}</p>
    </div>
    <button
      class="btn btn-primary open-edit-user-modal my-3"
      onclick='showCourseDetails(${JSON.stringify(course)})'
    >
      <i class="fas fa-eye"></i> مشخصات کامل دوره
    </button>
  </div>
</div>`;
}
