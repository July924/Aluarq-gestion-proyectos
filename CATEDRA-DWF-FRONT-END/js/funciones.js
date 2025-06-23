  function togglePassword() {
    const input = document.getElementById('inputPassword');
    const icono = document.getElementById('iconoOjo');
    
    if (input.type === 'password') {
      input.type = 'text';
      icono.classList.remove('fa-eye');
      icono.classList.add('fa-eye-slash');
    } else {
      input.type = 'password';
      icono.classList.remove('fa-eye-slash');
      icono.classList.add('fa-eye');
    }
  }

function calcularTotal() {
    const viaticos = parseFloat(document.getElementById('viaticos').value) || 0;
    const materiales = parseFloat(document.getElementById('materiales').value) || 0;
    const total = viaticos + materiales;
    document.getElementById('total').value = total.toFixed(2);
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('viaticos').addEventListener('input', calcularTotal);
    document.getElementById('materiales').addEventListener('input', calcularTotal);
});