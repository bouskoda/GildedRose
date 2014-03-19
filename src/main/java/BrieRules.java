public class BrieRules {
	private Item item;

	public BrieRules(Item item) {
		this.item = item;
	}

	public void incrementQuality() {
		item.setQuality(item.getQuality() + 1);
		
	}

}
