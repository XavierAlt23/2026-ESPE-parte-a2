package es.upm.grise.profundizacion.subscriptionService;

import java.util.Collection;
import java.util.HashSet;

public class SubscriptionService {

    private Collection<User> subscribers;

    /*
     * Constructor
     * - No hay ningún usuario suscrito al inicio
     */
    public SubscriptionService() {
        this.subscribers = new HashSet<>();
    }

    /*
     * Method to code / test
     */
    public void addSubscriber(User user) {

        if (user == null) {
            throw new NullUserException();
        }

        if (subscribers.contains(user)) {
            throw new ExistingUserException();
        }

        // Si el delivery es LOCAL, el email debe ser null
        if (user.getDelivery() == Delivery.LOCAL && user.getEmail() != null) {
            throw new LocalUserDoesNotHaveNullEMailException();
        }

        subscribers.add(user);
    }

    /*
     * Other setters & getters
     */
    public Collection<User> getSubscribers() {
        return subscribers;
    }
}
