
        document.addEventListener('DOMContentLoaded', async function() {
            // Elementos del formulario
            const form = document.getElementById('form-proyecto');
            const tipoProyectoSelect = document.getElementById('idTipoProyecto');
            const estadoSelect = document.getElementById('idEstado');
            const empleadoSelect = document.getElementById('idEmpleado');
            const submitBtn = document.getElementById('btn-submit');
            
            // Mostrar estado de carga
            submitBtn.disabled = true;
            
            // Función para cargar opciones desde la API
            async function loadOptions(endpoint, selectElement, valueField, textField) {
                try {
                    const response = await fetch(`http://localhost:8080/api/catalogo/${endpoint}`);
                    if (!response.ok) {
                        throw new Error(`Error ${response.status} al cargar ${endpoint}`);
                    }
                    
                    const data = await response.json();
                    
                    // Limpiar opciones existentes
                    selectElement.innerHTML = '';
                    
                    // Agregar opción por defecto
                    const defaultOption = document.createElement('option');
                    defaultOption.value = '';
                    defaultOption.textContent = `Seleccione ${endpoint.replace('-', ' ')}...`;
                    defaultOption.disabled = true;
                    defaultOption.selected = true;
                    selectElement.appendChild(defaultOption);
                    
                    // Agregar opciones desde la API
                    data.forEach(item => {
                        const option = document.createElement('option');
                        option.value = item[valueField];
                        option.textContent = item[textField];
                        selectElement.appendChild(option);
                    });
                    
                    return true;
                } catch (error) {
                    console.error(`Error al cargar ${endpoint}:`, error);
                    selectElement.innerHTML = `
                        <option value="" selected disabled>
                            Error al cargar ${endpoint}. ${error.message}
                        </option>
                    `;
                    return false;
                }
            }

            // Cargar todos los datos necesarios
            try {
                const loaders = [
                    loadOptions('tipos-proyecto', tipoProyectoSelect, 'idTipoProyecto', 'nombreTipoProyecto'),
                    loadOptions('estados', estadoSelect, 'idEstado', 'nombreEstado'),
                    loadOptions('empleados', empleadoSelect, 'idEmpleado', 'nombreEmpleado')
                ];
                
                await Promise.all(loaders);
                submitBtn.disabled = false;
            } catch (error) {
                console.error('Error al cargar datos iniciales:', error);
                alert('Error al cargar datos iniciales. Por favor recargue la página.');
            }
            
            // Calcular total automáticamente
            const viaticos = document.getElementById('viaticosAsignados');
            const materiales = document.getElementById('presupuestoMateriales');
            const total = document.getElementById('total');
            
            [viaticos, materiales].forEach(input => {
                input.addEventListener('input', () => {
                    const sum = (parseFloat(viaticos.value) || 0) + (parseFloat(materiales.value) || 0);
                    total.value = sum.toFixed(2);
                });
            });
            
            // Manejar envío del formulario
            form.addEventListener('submit', async function(e) {
                e.preventDefault();
                
                // Mostrar estado de carga en el botón
                submitBtn.disabled = true;
                submitBtn.classList.add('btn-loading');
                submitBtn.innerHTML = `
                    <i class="fas fa-spinner fa-spin spinner"></i>Guardando...
                `;
                
                try {
                    // Validar fechas
                    const fechaInicio = new Date(document.getElementById('fechaInicio').value);
                    const fechaFin = new Date(document.getElementById('fechaFin').value);
                    
                    if (fechaFin < fechaInicio) {
                        throw new Error('La fecha final no puede ser anterior a la fecha de inicio');
                    }
                    
                    // Validar selección de opciones
                    const idTipoProyecto = document.getElementById('idTipoProyecto').value;
                    const idEstado = document.getElementById('idEstado').value;
                    const idEmpleado = document.getElementById('idEmpleado').value;
                    
                    if (!idTipoProyecto || !idEstado || !idEmpleado) {
                        throw new Error('Debe seleccionar todas las opciones requeridas');
                    }
                    
                    // Obtener los datos del formulario
                    const formData = {
                        nombreProyecto: document.getElementById('nombreProyecto').value,
                        idTipoProyecto: parseInt(idTipoProyecto),
                        descripcionProyecto: document.getElementById('descripcionProyecto').value,
                        ubicacionProyecto: document.getElementById('ubicacionProyecto').value,
                        idEstado: parseInt(idEstado),
                        fechaInicio: document.getElementById('fechaInicio').value + ':00.000Z',
                        fechaFin: document.getElementById('fechaFin').value + ':00.000Z',
                        idEmpleado: parseInt(idEmpleado),
                        numeroTrabajadores: parseInt(document.getElementById('numeroTrabajadores').value),
                        viaticosAsignados: parseFloat(document.getElementById('viaticosAsignados').value),
                        presupuestoMateriales: parseFloat(document.getElementById('presupuestoMateriales').value)
                    };
                    
                    // Validar números positivos
                    if (formData.numeroTrabajadores <= 0 || 
                        formData.viaticosAsignados < 0 || 
                        formData.presupuestoMateriales < 0) {
                        throw new Error('Los valores numéricos deben ser positivos');
                    }
                    
                    // Enviar datos al backend
                    const response = await fetch('http://localhost:8080/api/proyectos', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(formData)
                    });
                    
                    if (!response.ok) {
                        const error = await response.json();
                        let errorMessage = 'Error al crear el proyecto';
                        
                        if (error.errors) {
                            // Si hay errores de validación
                            errorMessage = Object.values(error.errors).join('\n');
                        } else if (error.message) {
                            errorMessage = error.message;
                        }
                        
                        throw new Error(errorMessage);
                    }
                    
                    const data = await response.json();
                    alert('Proyecto creado exitosamente');
                    window.location.href = '../dashboard.html';
                    
                } catch (error) {
                    console.error('Error:', error);
                    alert(error.message || 'Error al procesar la solicitud');
                } finally {
                    // Restaurar el botón
                    submitBtn.disabled = false;
                    submitBtn.classList.remove('btn-loading');
                    submitBtn.innerHTML = `
                        <i class="fas fa-save me-2"></i>Guardar Proyecto
                    `;
                }
            });
        });