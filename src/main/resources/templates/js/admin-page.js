let users;
$(document).ready(function () {
  // For the first page load
  ajaxGetUsersAndShow(
    `http://localhost:8080/user/filter-users?firstName=&lastName=&gender=&role=&isActive=`
  );
  ajaxGetCoursesAndShow('http://localhost:8080/course/get-all');
  // Course

  // -------------------------------------------------------------------------------------
  // User
  // when admin click on search users
  $('#searchUsers').click((event) => {
    console.log('hello');
    let firstName = $('#firstNameInput').val();
    let lastName = $('#lastNameInput').val();
    let isActive = $('#isActive').val();
    let gender = $('#gender').val();
    let role = $('#role').val();
    let url = `http://localhost:8080/user/filter-users?firstName=${firstName}&lastName=${lastName}&gender=${
      gender == null ? '' : gender
    }&role=${role == null ? '' : role}&isActive=${
      isActive == null ? '' : isActive
    }`;
    ajaxGetUsersAndShow(url);
  });

  // set user information to modal
  $(document).on('click', '.open-edit-user-modal', function () {
    dataUsername = $(this).data('username');
    dataFirstName = $(this).data('first_name');
    dataLastName = $(this).data('last_name');
    dataEmail = $(this).data('email');
    dataGender = $(this).data('gender');
    dataRole = $(this).data('role');
    dataIsActive = $(this).data('is_active');
    $('.modal-body #usernameEditInput').val(dataUsername);
    $('.modal-body #firstNameEditInput').val(dataFirstName);
    $('.modal-body #lastNameEditInput').val(dataLastName);
    $('.modal-body #emailEditInput').val(dataEmail);
    $('#isActiveEditInput').prop('checked', dataIsActive);
    $('#genderEditInput').val(dataGender);
    $('#roleEditInput').val(dataRole);
  });

  // when admin click for save user changes
  $('#saveEditUser').click((event) => {
    let user = `{
      "username" : "${$('#usernameEditInput').val()}",
      "firstName" : "${$('#firstNameEditInput').val()}",
      "lastName" : "${$('#lastNameEditInput').val()}",
      "email" : "${$('#emailEditInput').val()}",
      "gender" : "${$('#genderEditInput').val()}",
      "role" : "${$('#roleEditInput').val()}",
      "isActive" : ${$('#isActiveEditInput').is(':checked')}
    }`;
    ajaxSaveUserChanges('http://localhost:8080/user/edit', user);
    location.reload();
    $('#editUserModal').modal('hide');
  });
  //------------------------------------------------------------------
});

// funcitons for Course
// ajax request for getting users

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

let courses;
function ajaxGetCoursesAndShow(url) {
  $.ajax({
    type: 'GET',
    url: url,
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

// button click event when we add course
$('#addCourseButton').click((event) => {
  event.preventDefault();
  let courseTitleInput = $('#courseTitleInput').val();
  let startDateInput = $('#startDateInput').val();
  let endDateInput = $('#endDateInput').val();
  if (courseTitleInput != '' && startDateInput != '' && endDateInput != '') {
    let course = `{
      "title": "${courseTitleInput}",
      "startDate": "${startDateInput}",
      "endDate": "${endDateInput}",
      "adminUsername": "${sessionStorage.getItem('username')}"
    }`;
    ajaxForAddCourse(course);
  } else {
    alert('لطفا تمام فیلد ها را پر کنید!');
  }
});

// Ajax for add a course
function ajaxForAddCourse(course) {
  $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/course/save',
    contentType: 'application/json',
    data: course,
    success: function (response) {
      alert('دوره با موفقیت اضافه شد.');
      location.reload();
    },
  });
}

// Ajax for getting teachers to show

// Add teacher to course btn click
function addTeacher(courseId) {
  $('#addTeacherToCourseModalBody').html('');
  for (let userIndex = 0; userIndex < users.length; userIndex++) {
    const user = users[userIndex];
    if (user.role == 'TEACHER') {
      $('#addTeacherToCourseModalBody').append(getTeacherAddModalHTML(user));
    }
  }
  $('#addTeacherToCourseModal').modal('hide');
  $('#addTeacherToCourseModal').modal('show');
}

// Get HTML Strings
function getTeacherAddModalHTML(teacher) {
  return `<div>
  <div class="form-check">
    <input
      class="form-check-input"
      type="radio"
      name="teacherRadio"
      id="${teacher.username}"
    />
    <label class="form-check-label" for="teacherRadio${teacher.username}">
      ${teacher.firstName} ${teacher.lastName}
    </label>
  </div>
</div>`;
}
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
    <div class="d-flex justify-content-between">
      <button
        class="btn btn-primary open-edit-user-modal w-100 mx-1"
        data-bs-toggle="modal"
        data-bs-target="#addTeacherToCourseModal"
        onclick="addTeacher()"
      >
        <i class="fas fa-plus"></i> استاد
      </button>
      <button
        class="btn btn-primary open-edit-user-modal w-100 mx-1"
        data-bs-toggle="modal"
        data-bs-target="#addStudentToCourseModal"
      >
        <i class="fas fa-plus"></i> دانشجو
      </button>
    </div>
    <button
      class="btn btn-primary open-edit-user-modal my-3"
      data-bs-toggle="modal"
      data-bs-target="#showCourseUsersModal"
    >
      <i class="fas fa-eye"></i> مشاهده لیست تمامی افراد
    </button>
  </div>
</div>`;
}

// ------------------------------------------------------------------------
// funcitons for User
function ajaxSaveUserChanges(urlSave, user) {
  $.ajax({
    type: 'POST',
    url: urlSave,
    contentType: 'application/json',
    data: user,
    success: function (response) {
      console.log('success');
    },
  });
}

// ajax request for getting users
function ajaxGetUsersAndShow(url) {
  $.ajax({
    type: 'GET',
    url: url,
    success: function (response) {
      $('#userContainer').html('');
      users = response;
      for (let userIndex = 0; userIndex < response.length; userIndex++) {
        const element = response[userIndex];
        let html = getShowUserHTML(element);
        $('#userContainer').append(html);
      }
    },
  });
}

// html creator for showing users
function getShowUserHTML(user) {
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
      <button class="btn btn-primary open-edit-user-modal" onclick="editUser('${
        user.username
      }')" data-bs-toggle="modal" data-bs-target="#editUserModal"
      data-username = ${user.username}
      data-first_name = '${user.firstName}'
      data-last_name = '${user.lastName}'
      data-email = '${user.email}'
      data-gender = '${user.gender}'
      data-role = '${user.role}'
      data-is_active = '${user.isActive}'
        ><i class="fas fa-user-edit fa-lg"></i
      ></button>
    </div>
  </div>
</div>`;
}

// -------------------------------------------------------------------------------------
// functions for Navbar
$('#showUsersItem').click((event) => {
  $('#usersShowing').removeClass('d-none');
  $('#courseShowing').addClass('d-none');
});
$('#showCoursesItem').click((event) => {
  $('#usersShowing').addClass('d-none');
  $('#courseShowing').removeClass('d-none');
});
$('#logoutItem').click((event) => {
  if (confirm('آیا می خواهید از حساب خود خارج شوید؟')) {
    sessionStorage.clear();
    window.location.href = 'index.html';
  }
});
