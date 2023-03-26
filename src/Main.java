import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Main extends JFrame {
    
	interest interestCalculator = new interest();
	
	private JTextField textField1 = new JTextField(10);
	JLabel label1 = new JLabel("Principal Amount:");
	private JTextField textField2 = new JTextField(10);
	JLabel label2 = new JLabel("Interest rate (as a percentage):");
	private JComboBox<String> comboBox = new JComboBox<String>(new String[]{"Daily", "Monthly", "Quarterly", "Annually"});
	JLabel label3 = new JLabel("Compounding period:");
	private JTextField textField4 = new JTextField(10);
	JLabel label4 = new JLabel("Time (years):");
	private JTextField textField5 = new JTextField(10);
	JLabel label5 = new JLabel("Monthly contribution:");
	private JTextField textField6 = new JTextField(10);
	JLabel label6 = new JLabel("Result:");
	
	
	
    private JButton button;
    
    double time, principal, rate, period, result, monthlyContribution;

    
    public Main() {
    	
    	
    	
        setTitle("Compound Interest Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Create the text field
        textField6.setEditable(false);

        
        XYSeries series = new XYSeries("Dollar Amount");
        XYSeriesCollection dataset = new XYSeriesCollection();
        

        // Create the renderer and set the spline shape
        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setSeriesShapesVisible(0, false);
        
        
     // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Dollar Amount Over Time", // chart title
                "Time (years)", // x-axis label
                "Dollar Amount", // y-axis label
                dataset, // data
                PlotOrientation.VERTICAL, // plot orientation
                true, // include legend
                true, // tooltips
                false // urls
        );
        
        // Create the button
        button = new JButton("Calculate amount");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                principal = Double.parseDouble(text1);
                
                String text2 = textField2.getText();
                rate = Double.parseDouble(text2);
                
                String periodStr = (String) comboBox.getSelectedItem();
                period = 0.0;
                switch (periodStr) {
                    case "Daily":
                        period = 365.0;
                        break;
                    case "Monthly":
                        period = 12.0;
                        break;
                    case "Quarterly":
                        period = 4.0;
                        break;
                    case "Annually":
                        period = 1.0;
                        break;
                }
                
                String text4 = textField4.getText();
                time = Double.parseDouble(text4);
                
                String text5 = textField4.getText();
                monthlyContribution = Double.parseDouble(text5);
                
             // Create the dataset
                
                for (int i = 0; i <= Math.ceil(time); i++) {
                    double x = i;
                    double y = interestCalculator.calculateInterest(principal + monthlyContribution*12, rate/100, period, x);
                    System.out.println(y);
                    series.add(x, y);
                    if(x == Math.ceil(time)) {
                    	result = Math.round(y*100.0)/100.0;
                    }
                }
                
                textField6.setText(Double.toString(result));
                dataset.addSeries(series);
             // Set the renderer for the chart
                XYPlot plot = (XYPlot) chart.getPlot();
                plot.setRenderer(renderer);
                
            }
        });

        


        // Display the chart
        ChartPanel chartPanel = new ChartPanel(chart);

        // Create a panel for the text field and button
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 50, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label1, gbc);
        gbc.gridy++;
        inputPanel.add(textField1, gbc);
        gbc.gridy++;
        inputPanel.add(label2, gbc);
        gbc.gridy++;
        inputPanel.add(textField2, gbc);
        gbc.gridy++;
        inputPanel.add(label3, gbc);
        gbc.gridy++;
        inputPanel.add(comboBox, gbc);
        gbc.gridy++;
        inputPanel.add(label4, gbc);
        gbc.gridy++;
        inputPanel.add(textField4, gbc);
        gbc.gridy++;
        inputPanel.add(label5, gbc);
        gbc.gridy++;
        inputPanel.add(textField5, gbc);
        gbc.gridy++;
        inputPanel.add(button, gbc);
        gbc.gridy++;
        inputPanel.add(label6, gbc);
        gbc.gridy++;
        inputPanel.add(textField6, gbc);
        
        
        // Create a panel for the chart and input panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.WEST);
        chartPanel.setPreferredSize(new Dimension(800, 500));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        getContentPane().add(panel);

        pack();
        setVisible(true);
        
    }

     
    public static void main(String[] args)
    {
        new Main();
        new interest();
        interest I = new interest();

       
    }
}