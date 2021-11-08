let course;
let students;
let allActiveTeachers;
let allActiveStudents;
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
      if (course.teacher == null) course.teacher = { id: -1 };
      $('#teachersContainerModal').append(getTeacherAddModalHTML(teacher));
    }
    $('#editTeacherModal').modal('hide');
    $('#editTeacherModal').modal('show');
  });

  // Save teacher changes click
  $('#saveEditCourseTeacher').click(function () {
    let teacherStringify = $('input[name="teacher"]:checked').val();
    updateCourseTeacher(teacherStringify);
  });

  // Add student to course btn click
  $('#editCourseStudents').click((event) => {
    $('#studentsContainerModal').html('');
    getAllActiveStudentsRequest();
    for (let i = 0; i < allActiveStudents.length; i++) {
      const student = allActiveStudents[i];
      let isExists = false;
      for (let j = 0; j < course.students.length; j++) {
        const courseStudent = course.students[j];
        if (courseStudent.id == student.id) {
          isExists = true;
          break;
        }
      }
      if (!isExists) {
        $('#studentsContainerModal').append(getStudentAddModalHTML(student));
        console.log('hello');
      }
    }
    $('#editStudentModal').modal('hide');
    $('#editStudentModal').modal('show');
  });

  // Save student changes click
  $('#saveEditCourseStudent').click(function () {
    let studentStringify = $('input[name="student"]:checked').val();
    updateCourseStudent(studentStringify);
  });
});

// Funcitons
// --------------------------------------------------------------------------------------------

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

// Ajax
// --------------------------------------------------------------------------------------------------

// Delete student
function deleteStudent(id) {
  $.ajax({
    type: 'DELETE',
    url: `http://localhost:8080/course/delete-student/${id}/${course.id}`,
    success: function (response) {
      location.reload();
    },
  });
}

// update course teacher
function updateCourseTeacher(teacher) {
  $.ajax({
    type: 'PUT',
    url: `http://localhost:8080/course/set-teacher?courseId=${course.id}`,
    contentType: 'application/json',
    data: teacher,
    success: function (response) {
      location.reload();
    },
  });
}

// update course student
function updateCourseStudent(student) {
  $.ajax({
    type: 'PUT',
    url: `http://localhost:8080/course/set-student?courseId=${course.id}`,
    contentType: 'application/json',
    data: student,
    success: function (response) {
      location.reload();
    },
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
  console.log(course);
  $.ajax({
    type: 'PUT',
    url: 'http://localhost:8080/course',
    contentType: 'application/json',
    data: JSON.stringify(course),
    success: function (response) {
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

// get all active students
function getAllActiveStudentsRequest() {
  $.ajax({
    type: 'GET',
    async: false,
    url: 'http://localhost:8080/user/filter-users?firstName=&lastName=&gender=&role=STUDENT&isActive=true',
    success: function (response) {
      allActiveStudents = response;
    },
  });
}

// get HTMLs
// --------------------------------------------------------------------------------------------------------------

function getStudentAddModalHTML(student) {
  return `<li class="list-group-item bg-secondary d-flex">
  <input
    id='studentInputRadio${student.id}'
    class="form-check-input me-1"
    type="radio"
    name="student"
    value=${JSON.stringify(student)}
    aria-label="..."
  />
  <label class="w-100 h-100 form-check-label" for="studentInputRadio${
    student.id
  }"> 
  ${student.firstName} ${student.lastName}</label>
</li>`;
}

function getTeacherAddModalHTML(teacher) {
  return `<li class="list-group-item bg-secondary  d-flex">
  <input
    id='teacherInputRadio${teacher.id}'
    class="form-check-input me-1"
    type="radio"
    name="teacher"
    value=${JSON.stringify(teacher)}
    aria-label="..."
    ${course.teacher.id == teacher.id ? 'checked' : ''}
  />
  <label class="w-100 h-100 form-check-label" for="teacherInputRadio${
    teacher.id
  }"> 
  ${teacher.firstName} ${teacher.lastName}</label>
</li>`;
}

function getStudentCardHTML(user) {
  return `<div class="card mb-4" style="width: 25rem; justify-self: center">
  <div class="card-body">
    <div class="card-title d-flex align-items-center">
      <div
        class="bg-secondary rounded-circle position-relative"
        style="width: 60px; height: 50px"
      >
        <span
          class="
            position-absolute
            top-10
            start-90
            translate-middle
            p-2
            bg-${user.isActive ? 'success' : 'danger'}
            border border-light
            rounded-circle
          "
        >
          <span class="visually-hidden">New alerts</span>
        </span>
      </div>
      <div class="d-flex justify-content-between w-100">
        <h5 class="mx-3">${user.firstName} ${user.lastName}</h5>
        <h5 class="mx-3 float-end"><i class="fas fa-${
          user.gender == 'MALE' ? 'male' : 'female'
        } fa-2x"></i></h5>
      </div>
    </div>
    <div class="d-flex">
      <p class="mx-1">ایمیل:</p>
      <p>${user.email}</p>
    </div>
    <div class="d-flex justify-content-between">
      <h6 class="h5">${user.role == 'STUDENT' ? 'دانشجو' : 'استاد'}</h6>
      <button onclick="deleteStudent(${user.id})" class="btn btn-danger">
      <i class="fas fa-user-minus"></i> حذف دانشجو
      </button>
    </div>
  </div>
</div>`;
}
{
  /* <button onclick="deleteStudent(${student.id})" class="btn btn-danger">
  <i class="fas fa-user-minus"></i> حذف دانشجو
</button>; */
}
