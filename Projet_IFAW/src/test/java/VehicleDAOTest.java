import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import persistantData.dao.CatalogData;
import persistantData.dao.VehicleDAO;
import persistantData.vehicles.Vehicle;

/**
 * JUnit test to test the VehicleDAO class
 *
 * @author Christian
 *
 */
public class VehicleDAOTest {

    private static VehicleDAO vehicleDAO;
    private static Vehicle vehicle;
    private static int id_v;

    @BeforeAll
    @Order(1)
    public static void setUp() {
        vehicleDAO = new VehicleDAO();
        String type = "Car";
        String category = "Race Car";
        String brand = "Sonic Team";
        String model = "Blue Blur";
        String engine = "Electric";
        String gearbox = "Automatic";
        int nb_seats = 1;
        String description = "Fastest car ever.";
        double price = 88000.15;
        String picture = "https://cdnb.artstation.com/p/assets/images/images/048/423/637/large/allan-mendez-blanco-allanspk-biddybuggy.jpg?1649983726";
        boolean available = true;
        java.sql.Date date_stock = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        boolean on_sale = false;
        int stock = 50;

        assertNull(CatalogData.getInstance().getVehicleByModel(model));
        Object[] data = new Object[]{0,type,category,brand,model,engine,gearbox,nb_seats,description,price,picture,available,date_stock,on_sale,stock};
        vehicle = vehicleDAO.createVehicleObject(data);

        id_v = vehicleDAO.insert(vehicle);
        assertTrue(id_v > 0);
        vehicle = vehicleDAO.get(id_v);
    }


    @Test
    @Order(2)
    public void testGetAllAvailable() {
        List<Vehicle> availableVehicles = vehicleDAO.getAllAvailable();
        assertNotNull(availableVehicles);
        assertFalse(availableVehicles.isEmpty());
    }

    @Test
    @Order(3)
    public void testGet() {
        Vehicle v = vehicleDAO.get(id_v);
        assertNotNull(v);
    }

    @Test
    @Order(4)
    public void testGetByModel() {
        String model = "Blue Blur";
        Vehicle v = vehicleDAO.getByModel(model);
        assertNotNull(v);
        assertEquals(model, v.getModel());
    }

    @Test
    @Order(5)
    public void testUpdateStock() {
        int newStock = 10;
        vehicleDAO.updateStock(vehicle, newStock);
        Vehicle v = vehicleDAO.get(id_v);
        assertEquals(v.getStock(), newStock);
    }

    @Test
    @Order(6)
    public void testDelete() {
        boolean deleted = vehicleDAO.delete(id_v);
        assertTrue(deleted);
    }
}