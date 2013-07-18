<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="reportSystemSum" property="chartCategory" id="chartCategory" type="java.lang.String" />
<bean:define name="reportSystemSum" property="chartSeriesContent" id="chartSeriesContent" type="java.lang.String" />
<bean:define name="reportSystemSum" property="curentChart" id="curentChart" type="java.lang.String" />
 <bean:define name="reportSystemSum" property="id" id="id" type="java.lang.Integer" />
<%=curentChart%>
<%=id%>
<script language=javascript>
     

                        var xCategories=<%=chartCategory%>;
                        var seriesContent=<%=chartSeriesContent%>;
                    
                      

		$(function() {
			
			Highcharts.setOptions({
				global : {
					useUTC : false
				}   
			});
			
			// Create the chart
			window.chart = new Highcharts.StockChart({
				chart : {
					renderTo : 'container',
					events : {
						load : function() {
		
							// set up the updating of the chart each second
							var series = this.series[0];
							setInterval(function() {
                                                        viewReportSum(<%=id%>);
								series.addPoint(<%=curentChart%>, true, true);
                                                               
							}, 60*1000);
						}
					}
				},
				
				rangeSelector: {
					buttons: [{
						count: 1,
						type: 'minute',
						text: '1M'
					}, {
						count: 5,
						type: 'minute',
						text: '5M'
					}, {
						type: 'all',
						text: 'All'
					}],
					inputEnabled: false,
					selected: 0
				},
				
				title : {
					text : 'Live random data'
				},
				
				exporting: {
					enabled: false
				},
				
				series :seriesContent
			});
		
		});
		
		</script>
 
	
		


