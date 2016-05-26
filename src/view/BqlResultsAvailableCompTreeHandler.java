package view;

public class BqlResultsAvailableCompTreeHandler {



	public static BqlResultsAvailableCompTreeHandler BQL_RES_TREE_HAND = null;
	public static BqlResultsAvailableCompTreeHandler createBQLResAvailableInst() {
		if(BQL_RES_TREE_HAND == null){
			BQL_RES_TREE_HAND = new BqlResultsAvailableCompTreeHandler();
			
		}
		return BQL_RES_TREE_HAND;
	}
	
	void moveToFronthandler()
	{
		BQLResDispComp.createBqlResDispInst().transformedSheetIF.moveToFront();
	}
}
