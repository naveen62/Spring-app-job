	console.log("print")
const forms = document.querySelectorAll('.needs-validation')


console.log(forms)
forms.forEach((form) => {
	form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
}, false)
})