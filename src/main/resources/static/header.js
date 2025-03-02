// Función para verificar la autenticación
async function checkAuth() {
    try {
        const response = await fetch("http://localhost:6060/OnlineShopping/users/check", {
            credentials: "include" // Incluir cookies en la solicitud
        });
        const data = await response.json();
        console.log("Respuesta del servidor:", data); // Depuración
        return data.authenticated; // Devuelve true o false
    } catch (error) {
        console.error("Error verificando sesión:", error);
        return false; // En caso de error, asumimos que no está autenticado
    }
}

// Función para cargar el header correcto
async function loadHeader() {
    const isAuthenticated = await checkAuth(); // Verificar autenticación
    const headerPath = isAuthenticated ? "/OnlineShopping/header2" : "/OnlineShopping/header";

    try {
        // Cargar el header correcto
        const response = await fetch(headerPath);
        if (!response.ok) {
            throw new Error(`Error al cargar el header: ${response.statusText}`);
        }
        const headerHtml = await response.text();
        document.getElementById('header_container').innerHTML = headerHtml;

        // Si el usuario está autenticado, agregar eventos al dropdown
        if (isAuthenticated) {
            const accountButton = document.querySelector(".account-button");
            const dropdownContent = document.querySelector(".dropdown-content");

            if (accountButton && dropdownContent) {
                accountButton.addEventListener("click", function (event) {
                    event.stopPropagation();
                    dropdownContent.classList.toggle("show");
                });

                document.addEventListener("click", function () {
                    dropdownContent.classList.remove("show");
                });

                // Manejar el cierre de sesión
                const logoutLink = document.querySelector("#logout-link");
                if (logoutLink) {
                    logoutLink.addEventListener("click", function (event) {
                        event.preventDefault();
                        localStorage.removeItem("userLoggedIn");
                        location.reload(); // Recargar página para volver al estado no autenticado
                    });
                }
            }
        }
    } catch (error) {
        console.error("Error cargando el header:", error);
    }
}

// Ejecutar al cargar la página
document.addEventListener("DOMContentLoaded", loadHeader);