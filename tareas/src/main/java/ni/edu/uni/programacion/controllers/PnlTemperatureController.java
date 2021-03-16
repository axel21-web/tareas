package ni.edu.uni.programacion.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import ni.edu.uni.programacion.views.panels.PnlTemperatura;

/**
 *
 * @author Sistemas-05
 */
public class PnlTemperatureController implements ActionListener {
    private PnlTemperatura pnlConversionTemplate;
    private final String TEMPERATURES[] = new String[]{"ºC", "ºF"};
    private DefaultComboBoxModel fromCmbModel;
    private DefaultComboBoxModel toCmbModel;
    
    public PnlTemperatureController(PnlTemperatura pnlConversionTemplate) {
        this.pnlConversionTemplate = pnlConversionTemplate;
        initComponent();
    }

    private void initComponent(){
        fromCmbModel = new DefaultComboBoxModel(TEMPERATURES);
        toCmbModel = new DefaultComboBoxModel(TEMPERATURES);
        pnlConversionTemplate.getCmbFrom().setModel(fromCmbModel);
        pnlConversionTemplate.getCmbTo().setModel(toCmbModel);
        
        pnlConversionTemplate.getBtnCalc().addActionListener(this);
        pnlConversionTemplate.getBtnNew().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Convertir")){
            int cmbFromIndex = pnlConversionTemplate.getCmbFrom().getSelectedIndex();
            int cmbToIndex = pnlConversionTemplate.getCmbTo().getSelectedIndex();
            double value = Double.parseDouble(pnlConversionTemplate.getTxtValue().getText());
            pnlConversionTemplate.getLblResult().setText("Resultado: " + convertidorTemperature(value, cmbFromIndex, cmbToIndex));
            
        }
	if(e.getActionCommand().equalsIgnoreCase("Nuevo")){
		pnlConversionTemplate.getTxtValue().setText("");
		pnlConversionTemplate.getLblResult().setText("Resultado: ");
	}
    }
    
    private double convertidorTemperature(double value, int from, int to){
        switch(from){
            case 0:
                switch(to){
                    case 0:
                        return value;                        
                    case 1:
                        return celciusToFahrenheit(value);
                }
            case 1:
                switch(to){
                    case 0:
                        return fahrenheitToCelcius(value);
                    case 1:
                        return value;
                }
        }
        return value;
    }
    
    private double celciusToFahrenheit(double value){        
        return (((double)9/5*value) + 32);
    }
    
    private double fahrenheitToCelcius(double value){
        return ((double)5/9 *(value - 32));
    }
}
