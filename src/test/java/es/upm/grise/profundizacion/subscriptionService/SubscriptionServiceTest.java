package es.upm.grise.profundizacion.subscriptionService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionServiceTest {

    @Test
    void constructor_iniciaSinSuscriptores() {
        SubscriptionService service = new SubscriptionService();
        assertNotNull(service.getSubscribers());
        assertTrue(service.getSubscribers().isEmpty());
    }

    @Test
    void addSubscriber_userNull_lanzaNullUserException() {
        SubscriptionService service = new SubscriptionService();

        assertThrows(NullUserException.class, () ->
                service.addSubscriber(null)
        );
    }

    @Test
    void addSubscriber_userValido_doNotDeliver_seAgrega() {
        SubscriptionService service = new SubscriptionService();

        User u = new User();
        u.setDelivery(Delivery.DO_NOT_DELIVER);
        u.setEmail("x@correo.com"); // permitido

        service.addSubscriber(u);

        assertEquals(1, service.getSubscribers().size());
        assertTrue(service.getSubscribers().contains(u));
    }

    @Test
    void addSubscriber_userDuplicado_lanzaExistingUserException() {
        SubscriptionService service = new SubscriptionService();

        User u = new User();
        u.setDelivery(Delivery.DO_NOT_DELIVER);
        u.setEmail("x@correo.com");

        service.addSubscriber(u);

        assertThrows(ExistingUserException.class, () ->
                service.addSubscriber(u)
        );
    }

    @Test
    void addSubscriber_localConEmailNoNulo_lanzaLocalUserDoesNotHaveNullEMailException() {
        SubscriptionService service = new SubscriptionService();

        User u = new User();
        u.setDelivery(Delivery.LOCAL);
        u.setEmail("no-debe@existir.com"); // NO permitido si es LOCAL

        assertThrows(LocalUserDoesNotHaveNullEMailException.class, () ->
                service.addSubscriber(u)
        );
    }

    @Test
    void addSubscriber_localConEmailNulo_seAgrega() {
        SubscriptionService service = new SubscriptionService();

        User u = new User();
        u.setDelivery(Delivery.LOCAL);
        u.setEmail(null); // requerido en LOCAL

        service.addSubscriber(u);

        assertEquals(1, service.getSubscribers().size());
        assertTrue(service.getSubscribers().contains(u));
    }
}
