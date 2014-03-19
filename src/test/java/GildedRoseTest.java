import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void qualityOfAStandardItemDegradesToZero() {
		setItems(new Item("Something", 2, 1));

		simulateDaysPastAs(1);

		assertThat(getFirstItem().getQuality(), is(0));
	}

	@Test
	public void qualityOfAnItemIsNeverNegative() {
		setItems(new Item("Something", 2, 1));

		simulateDaysPastAs(4);

		assertThat(getFirstItem().getQuality(), is(0));
	}
	
	@Test
	public void qualityOfAStandardItemDegradesTwiceAsFast() {
		setItems(new Item("Something", 2, 10));

		simulateDaysPastAs(3);

		assertThat(getFirstItem().getQuality(), is(6));
	}
	
	@Test
	public void agedBrieShouldIncreaseInQualityAsItGetsOlder() {
		setItems(new Item("Aged Brie", 2, 0));

		simulateDaysPastAs(1);

		assertThat(getFirstItem().getQuality(), is(greaterThan(0)));
	}

	@Test
	public void agedBrieShouldIncreaseTwoFoldInQualityAfterSellByDateHasPassed() {
		setItems(new Item("Aged Brie", 0, 40));

		simulateDaysPastAs(5);
		
		assertThat(getFirstItem().getSellIn(), is(-5)); 
		assertThat(getFirstItem().getQuality(), is(50));
	}

	@Test
	public void qualityOfAnItemNeverExceedsFifty() {
		setItems(new Item("Aged Brie", 2, 48));

		simulateDaysPastAs(5);
		
		assertThat(getFirstItem().getQuality(), is(50));
	}
	
	@Test
	public void qualityOfSulfurasDoesNotChange() {
		
		setItems(new Item("Sulfuras, Hand of Ragnaros", 2, 1));

		simulateDaysPastAs(4);

		assertThat(getFirstItem().getQuality(), is(1));
	}
	
	@Test
	public void qualityOfBackstagePassIncreasesByTwoWhenSellInIsBetweenFiveAndTenDays() {
		setItems(new Item("Backstage passes to a TAFKAL80ETC concert", 7, 20));
		
		simulateDaysPastAs(1);
		
		assertThat(getFirstItem().getQuality(), is(22));
	}
	
	@Test
	public void qualityOfBackstagePassIncreasesByTwoWhenSellInIsBetweenFiveAndZeroDays() {
		setItems(new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20));
		
		simulateDaysPastAs(1);
		
		assertThat(getFirstItem().getQuality(), is(23));
	}
	
	@Test
	public void qualityOfBackstagePassDropsToZeroAfterExpiry() {
		setItems(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20));
		
		simulateDaysPastAs(2);
		
		assertThat(getFirstItem().getQuality(), is(0));
	}
	
	@Ignore
	public void qualityOfConjuredItemDegradesTwiceAsFast() {
		setItems(new Item("Conjured Mana Cake", 1, 20));
		
		simulateDaysPastAs(1);
		
		assertThat(getFirstItem().getQuality(), is(18));
	}
	
	private Item getFirstItem() {
		List<Item> gildedRoseItems = GildedRose.getItems();
		return gildedRoseItems.get(0);
	}

	private void simulateDaysPastAs(int number) {
		for (int i = 0; i < number; i++) {
			GildedRose.updateQuality();
		}
	}

	private void setItems(Item item) {
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		GildedRose.setItems(items);
	}
}
