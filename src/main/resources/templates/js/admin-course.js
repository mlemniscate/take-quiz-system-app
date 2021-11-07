let course;
let students;
let allActiveTeachers;
$(document).ready(function () {
  let id = sessionStorage.getItem('courseId');
  findCourseById(id);
  findStudentsOfCourse(id);
  showCourse();

  // edit course info button
  $('#editCourseInfo').click((event) => {
    console.log('hello');
    $('#editCourseForm').toggleClass('d-none');
    $('#titleInput').val($('#title').html());
    $('#startDateInput').val($('#dateStart').html());
    $('#endDateInput').val($('#dateEnd').html());
  });

  // edit course save button
  $('#saveEditCourse').click((event) => {
    course.title = $('#titleInput').val();
    course.startDate = $('#startDateInput').val();
    course.endDate = $('#endDateInput').val();
    updateCourse();
  });

  // back button functionality
  $('#backButton').click((event) => {
    window.location.href = 'admin-page.html';
  });

  // Add teacher to course btn click
  $('#editCourseTeacher').click((event) => {
    $('#teachersContainerModal').html('');
    getAllActiveTeachersRequest();
    for (let i = 0; i < allActiveTeachers.length; i++) {
      const teacher = allActiveTeachers[i];
      $('#teachersContainerModal').append(getTeacherAddModalHTML(teacher));
    }
    $('#editTeacherModal').modal('hide');
    $('#editTeacherModal').modal('show');
  });
});

// show course first info
function showCourse() {
  console.log(course['startDate']);
  $('#title').html(course.title);
  $('#dateEnd').html(course.endDate);
  $('#dateStart').html(course.startDate);
  if (course.teacher != null) {
    $('#teacher').html(
      `${course.teacher.firstName} ${course.teacher.lastName}`
    );
  } else $('#teacher').html('ندارد');
  students.forEach((student) => {
    console.log(student);
    $('#studentsContainer').append(getStudentCardHTML(student));
  });
}

// find students of a course
function findStudentsOfCourse(id) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/get-all-students/${id}`,
    async: false,
    success: function (response) {
      students = response;
    },
  });

  // jalali date picker picking date of courses
  let startDateInput = document.getElementById('startDateInput');
  jalaliDatepicker.startWatch();
  startDateInput.addEventListener('focus', (event) => {
    jalaliDatepicker.show(startDateInput);
  });
  let endDateInput = document.getElementById('endDateInput');
  jalaliDatepicker.startWatch();
  endDateInput.addEventListener('focus', (event) => {
    jalaliDatepicker.show(endDateInput);
  });
}

// find course by id
function findCourseById(id) {
  $.ajax({
    type: 'GET',
    url: `http://localhost:8080/course/${id}`,
    async: false,
    success: function (response) {
      course = response;
    },
  });
}

// update course
function updateCourse() {
  $.ajax({
    type: 'PUT',
    url: 'http://localhost:8080/course',
    contentType: 'application/json',
    data: JSON.stringify(course),
    success: function (response) {
      alert('تغییرات با موفقیت ثبت شد.');
      location.reload();
    },
  });
}

// get all active teachers
function getAllActiveTeachersRequest() {
  $.ajax({
    type: 'GET',
    async: false,
    url: 'http://localhost:8080/user/filter-users?firstName=&lastName=&gender=&role=TEACHER&isActive=true',
    success: function (response) {
      allActiveTeachers = response;
    },
  });
}

function getTeacherAddModalHTML(teacher) {
  return `<li class="list-group-item bg-secondary">
  <input
    class="form-check-input me-1"
    type="radio"
    name="teacher"
    value=""
    aria-label="..."
  />
  ${teacher.firstName} ${teacher.lastName}
</li>`;
}

function getStudentCardHTML(student) {
  return `<div class="d-flex justify-content-between align-items-center my-4">
  <div class="d-flex align-items-end">
    <div
      class="bg-white rounded-circle position-relative"
      style="width: 50px; height: 50px"
    ></div>
    <p class="mx-3"><b>${student.firstName} ${student.lastName}</b></p>
  </div>
  <div>
    <button class="btn btn-danger">
      <i class="fas fa-user-minus"></i> حذف دانشجو
    </button>
  </div>
</div><hr/>`;
}
