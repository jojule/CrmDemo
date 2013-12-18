package org.vaadin.demo.crm.ui;

import org.vaadin.demo.crm.Backend;
import org.vaadin.demo.crm.data.PipelineStage;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.touchkit.ui.NavigationView;

public class Stats extends NavigationView {
	public Stats() {
		Chart c = createChart(Backend.getPipelineReport());
		setContent(c);
	}
	
    public static Chart createChart(PipelineStage[] pipelineStages) {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Pipeline split");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setColor(new SolidColor(0, 0, 0));
        dataLabels.setConnectorColor(new SolidColor(0, 0, 0));
        dataLabels
                .setFormatter("''+ this.point.name +': '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        final DataSeries series = new DataSeries();
        for (int i = 0; i < pipelineStages.length; i++) {
        	PipelineStage s = pipelineStages[i];
            series.add(new DataSeriesItem(s.getName(), Math.round(s.getTotalValue())));			
		}
        
        conf.setSeries(series);
        

        chart.drawChart(conf);

        return chart;
    }
}
