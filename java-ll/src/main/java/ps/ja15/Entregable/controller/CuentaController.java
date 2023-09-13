package ps.ja15.Entregable.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.ja15.Entregable.controller.model.ApiResponseMessage;
import ps.ja15.Entregable.controller.model.HttpStatusMessages;
import ps.ja15.Entregable.model.Cuenta;
import ps.ja15.Entregable.services.CuentaService;
import java.util.ArrayList;


@RestController
@RequestMapping("/cuentas")
@Api(value = "Cuentas API V1", description = "API Cuentas")
public class CuentaController {


    private final CuentaService cuentaService;



    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }



    @GetMapping
    @ApiOperation(value = "Obtener Listado de cuentas", response = ApiResponse.class)
    public ResponseEntity<?> findByAll() {

        try {

            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.findByAll()).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }

    }

    @GetMapping("/{numeroCuenta}")
    @ApiOperation(value = "Obtener cuenta por numero de cuenta", response = ApiResponse.class)
    public ResponseEntity<?> obtenerCuentaPorId(@PathVariable String numeroCuenta) throws Exception {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.findByNumberCount(numeroCuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.save(cuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }

    }

    @PutMapping
    public ResponseEntity<?> actualizarCuenta(@RequestBody Cuenta cuenta) {
        try {
        return ResponseEntity.ok().body(ApiResponseMessage.builder()
                .status(HttpStatusMessages.OK.getStatusCode())
                .message(HttpStatusMessages.OK.getStatusMessage())
                .data(cuentaService.update(cuenta)).build());


    } catch (Exception e) {
        return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                .data(new ArrayList<Cuenta>()).build());
    }
    }

    @DeleteMapping("/{numeroDeCuenta}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable String numeroDeCuenta) {
        try {
            cuentaService.delete(numeroDeCuenta);
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(null).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(null).build());
        }
    }

}
