$('#showMessage').html(sessionStorage.getItem('endQuizMessage'));
$('#backItem').click((event) => {
  window.location.href = 'student-course.html';
});
