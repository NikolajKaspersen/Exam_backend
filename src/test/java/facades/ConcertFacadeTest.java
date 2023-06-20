//package facades;
//
//import dtos.ConcertDto;
//import entities.Concert;
//import facades.ConcertFacade;
//import org.junit.jupiter.api.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ConcertFacadeTest {
//
//    private static EntityManagerFactory emf;
//    private static ConcertFacade facade;
//
//    @BeforeAll
//    public static void setUpClass() {
//        emf = Persistence.createEntityManagerFactory("your-persistence-unit"); // Erstat "your-persistence-unit" med dit enhedsnavn for Persistence Unit
//        facade = ConcertFacade.getConcertFacade(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//        emf.close();
//    }
//
//    @BeforeEach
//    public void setUp() {
//        // Initialiser testdata eller nulstil database før hver test
//        // F.eks. kan du oprette nogle testkoncerter ved hjælp af ConcertFacade.create()
//        // og slette dem efter hver test ved hjælp af ConcertFacade.deleteById()
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createQuery("DELETE FROM Concert").executeUpdate();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Ryd op efter hver test om nødvendigt
//    }
//
//    @Test
//    public void testGetAll() {
//        // Test getAll-metoden
//        List<ConcertDto> concerts = facade.getAll();
//        assertNotNull(concerts);
//        assertEquals(0, concerts.size()); // Tilpas antallet baseret på testdataene
//    }
//
//    @Test
//    public void testCreateAndGetById() {
//        // Test create- og getById-metoderne
//        ConcertDto createdConcert = facade.create(new ConcertDto() );
//
//        assertNotNull(createdConcert);
//        assertNotNull(createdConcert.getId());
//
//        ConcertDto retrievedConcert = facade.getById(createdConcert.getId());
//
//        assertNotNull(retrievedConcert);
//        assertEquals(createdConcert.getId(), retrievedConcert.getId());
//        // Tilføj flere assertions for at kontrollere, om de andre attributter matcher
//    }
//
//    @Test
//    public void testUpdate() {
//        // Test update-metoden
//        ConcertDto concertDto = new ConcertDto(/* koncertattributter */);
//        ConcertDto createdConcert = facade.create(concertDto);
//
//        // Opdater attributterne for createdConcert
//        createdConcert.setName("Updated Name");
//        createdConcert.setDuration("Updated Duration");
//        createdConcert.setLocation("Updated Location");
//        createdConcert.setStartDate("Updated Start Date");
//        createdConcert.setStartTime("Updated Start Time");
//
//        ConcertDto updatedConcert = facade.update(createdConcert);
//
//        assertNotNull(updatedConcert);
//        assertEquals(createdConcert.getId(), updatedConcert.getId());
//        // Tilføj assertions for at kontrollere, om de andre attributter er blevet opdateret korrekt
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Test deleteById-metoden
//        ConcertDto concertDto = new ConcertDto(/* koncertattributter */);
//        ConcertDto createdConcert = facade.create(concertDto);
//
//        Long concertId = createdConcert.getId();
//        ConcertDto deletedConcert = facade.deleteById(concertId);
//
//        assertNotNull(deletedConcert);
//        assertEquals(concertId, deletedConcert.getId());
//        // Tilføj assertions for at kontrollere, om de andre attributter er blevet slettet korrekt
//
//        ConcertDto retrievedConcert = facade.getById(concertId);
//        assertNull(retrievedConcert);
//    }
//}
