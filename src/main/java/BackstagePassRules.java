
public class BackstagePassRules {

	private Item item;

	public BackstagePassRules(Item item) {
		this.item = item;
	}
	
	public void updateQualityForBackstagePassItem() {
		if (item.getSellIn() < 11)
		{
		    if (item.getQuality() < 50)
		    {
		        item.setQuality(item.getQuality() + 1);
		    }
		}

		if (item.getSellIn() < 6)
		{
		    if (item.getQuality() < 50)
		    {
		        item.setQuality(item.getQuality() + 1);
		    }
		}
	}

	public void setQualityToZero() {
        item.setQuality(0);
	}


}
