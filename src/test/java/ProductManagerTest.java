import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class ProductManagerTest {
    private ProductRepository repository = Mockito.mock(ProductRepository.class);
    private ProductManager manager = new ProductManager(repository);

    private Book first = new Book(1, "The Adventure of Tom Sewer", 500, "Mark Twain");
    private Smartphone second = new Smartphone(2, "Apple iPhone 11", 55000, "Apple");
    private Book third = new Book(3, "Apple", 300, "V. Suteyev");
    private Smartphone fourth = new Smartphone(4, "Honor 30i", 17000, "Huawei");
    private Book fifth = new Book(5, "Huckleberry Finn", 450, "Mark Twain");
    private Smartphone sixth = new Smartphone(6, "Honor 10i", 12000, "Huawei");
    private Smartphone seventh = new Smartphone(7, "Galaxy S21", 70000, "Samsung");

    @BeforeEach
    public void createRepository() {
        Product[] repo = {first, second, third, fourth, fifth, sixth, seventh};
        doReturn(repo).when(repository).findAll();
    }

    @AfterEach
    public void verifyMock() {
        verify(repository).findAll();
    }

    @Test
    public void shouldNoMatchesBook() {
        Product[] actual = manager.searcyBy("Dickens");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNoMatchesSmartphone() {
        Product[] actual = manager.searcyBy("Lenovo");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneMatchesBookAutor() {
        Product[] actual = manager.searcyBy("V. Suteyev");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneMatchesBookName() {
        Product[] actual = manager.searcyBy("Huckleberry Finn");
        Product[] expected = new Product[]{fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneMatchesSmartphoneName() {
        Product[] actual = manager.searcyBy("Apple iPhone 11");
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneMatchesSmartphoneManufacturer() {
        Product[] actual = manager.searcyBy("Samsung");
        Product[] expected = new Product[]{seventh};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTwoMatchesBookAutor() {
        Product[] actual = manager.searcyBy("Mark Twain");
        Product[] expected = new Product[]{first, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTwoMatchesSmartphoneManufacturer() {
        Product[] actual = manager.searcyBy("Huawei");
        Product[] expected = new Product[]{fourth, sixth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTwoMatchesBookSmartphone() {
        Product[] actual = manager.searcyBy("Apple");
        Product[] expected = new Product[]{second, third};
        assertArrayEquals(expected, actual);
    }
}
