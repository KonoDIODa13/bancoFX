1ª PRÁCTICA: REPASO PROGRAMACIÓN: Cuentas Bancarias

Para iniciarla, hay que dirigirse al main que esta en la ruta: "src/mainjava/application/banco/Main.java".
----------------------------------------------------------------------------------------------------------
Descripción del sistema de carpetas/paquetes:


  Application/banco:

    La carpeta que crea al crear un nuevo proyecto. En ella, esta el main de la aplicación.

    ----------------------------------------------------------------------------------------
  Application/controller:
    La carpeta donde estarán todos los controladores que he visto necesarios para la aplicación. en este caso solo hay uno que lo controla todo.
      controller: En ella, instancio un objeto de clase Banco para acceder a los métodos que necesito que realice y todas las referencias a los ids de cada uno de los TextFields, Texts ToggleGroups Buttons, el ListView y una variable de control de tipo entero tipoCuenta.
      
      Empieza con 3 metodos parecidos en los que desativa y muestra los datos en función de lo que necesite (Ej si es una cuenta Ahorro, necesita el interes Etc...) y esconde el resto. Ademas, modifica el tipoCuenta a la hora de crear una nueva cuenta.
      
      El método abrirCuenta compruebas los campos y en el campo de IBAN comprueba si es de tipo ESNNNNNNNNNNNNNNNNNNNN. Para ello, utilizo la clase Pattern y Matcher que son dos clases para comprobar expresiones regulares. Después, este metodo llama a annadirTipoCuenta que en función del tipo de cuenta seleccionado, insertará la cuenta. A la hora de insertar, comprueba en la clase Banco si existe o no la cuenta o una cuenta con el mismo IBAN. Si está todo correcto, mostrará un mensaje de cuenta insertada con exito, limpiará los campos e insertará la cuenta en el ListView. Si no, mostrará el error de no se ha podido insertar una cuenta o el del IBAN no correcto.

      El método de limpiarCampos es un metodo sencillo que limpia todos los campos del insertar cuenta.

      El método de buscarCuenta buscará la cuenta con el IBAN seleccionado. para ello, utilizo el stream que es una forma moderna de recorrer arrays que te permite una serie de metodos para reducir el código aunque lo pueda hacer más difícil de entender para los nuevos programadores. En este caso, uso el filter que filtra segun la condicion que le pongamos, en este caso si el IBAN que pasamos es igual que el de la Cuenta. si no lo encuentra, nos devuelve null y por tanto un mensaje de cuenta no encontrada. En caso positivo, encontrará la ceunta y nos dejará hacer los siguientes movimientos (obtener saldo, ingresar y retirar).

      El método ingresar, ingresará la cantidad pasada por texto al saldo total de la cuenta. El de retirar hara justo lo contrario aunque antes comprueba si la cantidad a sacar es mayor que la del saldo la que dará un error. El de obtenerSaldo nos mostrará un mensaje con el saldo de la cuenta.

      El método de limpiarCamposOpciones hará lo mismo que el limpiarCampos solo que solo limpiará los campos de las opciones de cuentas.

      Por último, el método salir que nos mostrará una pantallita sobre si queremos salir o no, si es que si, acabará el programa, si no, seguira tal cual está. Además, están los métodos de comprobaciones con diferentes comprobaciones.

    --------------------------------------------------------------------------------------

  Application/CRUD:
    La carpeta donde estarán todos las clases que contendrán todas las acciones que el usuario de la aplicación pueda realizar, en mi caso, la clase Banco que tiene un ArrayList de CuentasBancarias al que añadiremos las cuentas que creemos.

    Tendrá el método de abrirCuenta con el que abriremos la cuenta, el de listadoCuentas utilizado para volcar el array de Cuentas al ListView (realizo un copy of para que sea inmutable y no me se pueda modificar), el de informacion de cuenta que buscará una la cuenta mediante su IBAN y los de ingreso , retirada y obtenerSaldo que ya he comentado.
  
    --------------------------------------------------------------------------------------
  Application/Domain:
    La carpeta donde estarán todas las clases que se van a utilizar en la aplicación. En mi caso, todas aquellas clases que van a definir los diferentes tipos de cuentas que se pueden crear.

    La clase Persona guardará los datos del nombre, apellidos y DNI del usuario de la cuenta.
    La clase CuentaBancara será abstracta y tendrá los atributos del titular (la Persona de la cuenta), el IBAN y el saldo de cuenta. de ella se extenderán el resto de clases.
    La clase CuentaAhorro extenderá de CuentaBancaria y tendrá como atributo el de interés.
    La clase CuentaCorriente será abstracta y extenderá de CuentaBancaria.
    La clase CuentaPersonal que extenderá de CuentaCorriente y tendrá el atributo de mantenimiento.
    La clase CuentaEmpresa que extenderá de CuentaCorriente y tendrá los atributos de interés descubierto y de máximo de descubierto.

    ------------------------------------------------------------------------------------
  Application/utils:
    La carpeta en la que añadiré algunas clases que vea útiles para mi aplicación.

      En mi caso, el de los Alerts que tiene ods metodos, mostrarError, que mostrará el error y la descripción del error y el de mostrarConfirmación que mostrará que está correcto.
    ------------------------------------------------------------------------------------
  Resources/Application/Banco
    La carpeta de recursos en el que se guarda por defecto el archivo FXML para ver la interfaz de la aplicación.
  
