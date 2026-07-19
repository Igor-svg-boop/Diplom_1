import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        // Создаем моки для булок
        Bun blackBun = mock(Bun.class);
        when(blackBun.getName()).thenReturn("black bun");
        when(blackBun.getPrice()).thenReturn(100f);

        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getName()).thenReturn("white bun");
        when(whiteBun.getPrice()).thenReturn(1f);

        Bun redBun = mock(Bun.class);
        when(redBun.getName()).thenReturn("red bun");
        when(redBun.getPrice()).thenReturn(1f);

        // Создаем моки для ингредиентов (первый набор)
        Ingredient sourCream = mock(Ingredient.class);
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);
        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getPrice()).thenReturn(200f);

        Ingredient cutlet1 = mock(Ingredient.class);
        when(cutlet1.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet1.getName()).thenReturn("cutlet");
        when(cutlet1.getPrice()).thenReturn(100f);

        Ingredient dinosaur1 = mock(Ingredient.class);
        when(dinosaur1.getType()).thenReturn(IngredientType.FILLING);
        when(dinosaur1.getName()).thenReturn("dinosaur");
        when(dinosaur1.getPrice()).thenReturn(200f);

        // Создаем моки для ингредиентов (второй набор)
        Ingredient cutlet2 = mock(Ingredient.class);
        when(cutlet2.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet2.getName()).thenReturn("cutlet");
        when(cutlet2.getPrice()).thenReturn(0.5f);

        Ingredient chiliSauce = mock(Ingredient.class);
        when(chiliSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(chiliSauce.getName()).thenReturn("chili sauce");
        when(chiliSauce.getPrice()).thenReturn(4f);

        Ingredient sausage1 = mock(Ingredient.class);
        when(sausage1.getType()).thenReturn(IngredientType.FILLING);
        when(sausage1.getName()).thenReturn("sausage");
        when(sausage1.getPrice()).thenReturn(1f);

        // Создаем моки для ингредиентов (третий набор)
        Ingredient hotSauce = mock(Ingredient.class);
        when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(hotSauce.getName()).thenReturn("hot sauce");
        when(hotSauce.getPrice()).thenReturn(5.5f);

        Ingredient sausage2 = mock(Ingredient.class);
        when(sausage2.getType()).thenReturn(IngredientType.FILLING);
        when(sausage2.getName()).thenReturn("sausage");
        when(sausage2.getPrice()).thenReturn(14.5f);

        Ingredient dinosaur2 = mock(Ingredient.class);
        when(dinosaur2.getType()).thenReturn(IngredientType.FILLING);
        when(dinosaur2.getName()).thenReturn("dinosaur");
        when(dinosaur2.getPrice()).thenReturn(200f);

        return new Object[][]{
                {
                        blackBun,
                        Arrays.asList(sourCream, cutlet1, dinosaur1),
                        "(==== black bun ====)" + System.lineSeparator() +
                                "= sauce sour cream =" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "= filling dinosaur =" + System.lineSeparator() +
                                "(==== black bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 700,000000" + System.lineSeparator()
                },
                {
                        whiteBun,
                        Arrays.asList(cutlet2, chiliSauce, sausage1),
                        "(==== white bun ====)" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "= sauce chili sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "(==== white bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 7,500000" + System.lineSeparator()
                },
                {
                        redBun,
                        Arrays.asList(hotSauce, sausage2, dinosaur2),
                        "(==== red bun ====)" + System.lineSeparator() +
                                "= sauce hot sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "= filling dinosaur =" + System.lineSeparator() +
                                "(==== red bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 222,000000" + System.lineSeparator()
                },
        };
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        String receipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, receipt);
    }
}