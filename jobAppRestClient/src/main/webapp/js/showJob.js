const myModal = document.getElementById('profile-model')
const myInput = document.getElementById('modal-in')

myModal.addEventListener('shown.bs.modal', () => {
  myInput.focus()
})