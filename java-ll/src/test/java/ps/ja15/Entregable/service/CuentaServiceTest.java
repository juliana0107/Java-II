package ps.ja15.Entregable.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ps.ja15.Entregable.model.Cuenta;
import ps.ja15.Entregable.services.CuentaService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentaServiceTest {

    @InjectMocks
    private CuentaService cuentaService; // Utiliza la implementación concreta

    @Mock
    private CuentaRepository cuentaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCuenta() throws Exception {
        // Simula el comportamiento del repositorio
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.empty());
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(new Cuenta());

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldo(BigDecimal.valueOf(1000));

        Cuenta savedCuenta = cuentaService.save(cuenta);

        assertNotNull(savedCuenta);
    }

    @Test
    public void testUpdateCuentaNotFound() throws Exception {
        // Simula el comportamiento del repositorio para una cuenta inexistente
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.empty());
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(new Cuenta());
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldo(BigDecimal.valueOf(1000));

        assertThrows(Exception.class, () -> cuentaService.update(cuenta));
    }

    @Test
    public void testDeleteCuenta() throws Exception {
        // Simula el comportamiento del repositorio
        Cuenta cuentaMook= new Cuenta();
        cuentaMook.setSaldo(new BigDecimal(100));
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.of(cuentaMook));

        assertDoesNotThrow(() -> cuentaService.delete("123456"));
    }

    @Test
    public void testDeleteCuentaNotFound() {
        // Simula el comportamiento del repositorio para una cuenta inexistente
        when(cuentaRepository.findByNumeroCuenta(anyString())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> cuentaService.delete("123456"));
    }


}
