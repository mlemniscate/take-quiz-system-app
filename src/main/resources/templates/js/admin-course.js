let course;
$(document).ready(function () {
  findCourseById(sessionStorage.getItem('courseId'));
  // showCourse(course);
});

// find course by id
function findCourseById(id) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/${id}`,
    contentType: 'application/json',
    data: course,
    success: function (response) {
      console.log(response);
    },
  });
}
