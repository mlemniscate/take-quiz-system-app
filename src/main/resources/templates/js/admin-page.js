$(document).ready(function () {
  ajaxGetUsersAndShow(
    `http://localhost:8080/user/filter-users?firstName=&lastName=&gender=&role=&isActive=`
  );
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
    console.log(url);
    ajaxGetUsersAndShow(url);
  });
});

function ajaxGetUsersAndShow(url) {
  $.ajax({
    type: 'GET',
    url: url,
    success: function (response) {
      $('#userContainer').html('');
      for (let userIndex = 0; userIndex < response.length; userIndex++) {
        const element = response[userIndex];
        let html = getShowUserHTML(element);
        $('#userContainer').append(html);
      }
    },
  });
}

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
          user.gender == 'man' ? 'male' : 'female'
        }"></i></h5>
      </div>
    </div>
    <div class="d-flex">
      <p class="mx-1">ایمیل:</p>
      <p>${user.email}</p>
    </div>
    <div class="d-flex justify-content-between">
      <h6>${user.role == 'STUDENT' ? 'دانشجو' : 'استاد'}</h6>
      <a href="#" class="btn btn-primary"
        ><i class="fas fa-user-edit"></i
      ></a>
    </div>
  </div>
</div>`;
}
