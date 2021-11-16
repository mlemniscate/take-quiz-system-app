let courses;
let teacher;

// -------------------------------------------------------------------------------------
// Page loads
// Teacher loads
ajaxGetTeacherByUsername();

// Course loads
ajaxGetCoursesAndShow();

// Show teacher name
$('#teacherFullName').html(`${teacher.firstName} ${teacher.lastName}`);

// -------------------------------------------------------------------------------------
// functions for Navbar
$('#logoutItem').click((event) => {
  if (confirm('آیا می خواهید از حساب خود خارج شوید؟')) {
    sessionStorage.clear();
    window.location.href = 'index.html';
  }
});

// course details click event
function showCourseDetails(id) {
  sessionStorage.setItem('courseId', id);
  sessionStorage.setItem('teacherId', teacher.id);
  window.location.href = 'teacher-course.html';
}

// ajax for get teacher
function ajaxGetTeacherByUsername() {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/teacher/get-by-username?username=${sessionStorage.getItem(
      'username'
    )}`,
    async: false,
    success: function (response) {
      teacher = response;
    },
  });
}

// ajax for get courses and show them
function ajaxGetCoursesAndShow(url) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/get-teacher-courses/${teacher.id}`,
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
      onclick="showCourseDetails(${course.id})"
    >
      <i class="fas fa-eye"></i> مشخصات کامل دوره
    </button>
  </div>
</div>`;
}
