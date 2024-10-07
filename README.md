1ª PRÁCTICA: REPASO PROGRAMACIÓN: Cuentas Bancarias

Para iniciarla, hay que dirigirse al main que esta en la ruta: "src/mainjava/application/banco/Main.java".

Descripción del sistema de carpetas/paquetes:\n
  Application/banco:
    La carpeta que crea al crear un nuevo proyecto. En ella, esta el main de la aplicación.
  Application/controller:
    La carpeta donde estarán todos los controladores que he visto necesarios para la aplicación. en este caso solo hay uno que lo controla todo.
      controller: En ella, instancio un objeto de clase Banco para acceder a los métodos que necesito que realice y todas las referencias a los ids de cada uno de los TextFields, Texts ToggleGroups Buttons, el ListView y una variable de control de tipo entero tipoCuenta.
      Empieza con 3 metodos parecidos en los que desativa y muestra los datos en función de lo que necesite (Ej si es una cuenta Ahorro, necesita el interes Etc...) y esconde el resto. Ademas, modifica el tipoCuenta a la hora de crear una nueva cuenta.
      El método
