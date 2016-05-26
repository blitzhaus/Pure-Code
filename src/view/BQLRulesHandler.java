package view;

public class BQLRulesHandler {

	public static BQLRulesHandler BQL_RULES_HANDLER = null;
	public static BQLRulesHandler createRulesHandlerInst() {
		if(BQL_RULES_HANDLER == null){
			BQL_RULES_HANDLER = new BQLRulesHandler("just object creation");
		}
		return BQL_RULES_HANDLER;
	}
	public BQLRulesHandler(String dummy){
		
	}
	void lloqCheckHandler() {
		if(BQLSetupDispComp.createBQLSetupDispInst().lloqCheckBox.isSelected() == true){
			BQLSetupDispComp.createBQLSetupDispInst().lloqText.setEditable(true);
			BQLSetupDispComp.createBQLSetupDispInst().lloqText.setEnabled(true);
		} else if(BQLSetupDispComp.createBQLSetupDispInst().lloqCheckBox.isSelected() == false){
			BQLSetupDispComp.createBQLSetupDispInst().lloqText.setEditable(false);
			BQLSetupDispComp.createBQLSetupDispInst().lloqText.setEnabled(false);
		}
		
		
	}

}
