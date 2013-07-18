<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="reportSystemSum" property="chartCategory" id="chartCategory" type="java.lang.String" />
<bean:define name="reportSystemSum" property="chartSeriesContent" id="chartSeriesContent" type="java.lang.String" />
<bean:define name="reportSystemSum" property="typeChart" id="typeChart" type="java.lang.Integer" />
<bean:define name="reportSystemSum" property="maxZoom" id="maxZoom" type="java.lang.Integer" />
<bean:define name="reportSystemSum" property="timeRefresh" id="timeRefresh" type="java.lang.Integer" />
<bean:define name="reportSystemSum" property="id" id="id" type="java.lang.Integer" />
<script language=javascript>

			var chart;
                        var xCategories=<%=chartCategory%>;
                        var seriesContent=<%=chartSeriesContent%>;
<%if(typeChart==0){%>
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'line',
						marginRight: 130,
						marginBottom: 25
					},
					title: {
						text: '',
						x: -20 //center
					},
					xAxis: {
						categories: xCategories
					},
					yAxis: {
						title: {
							text: 'Total sms'
						},
						plotLines: [{
							value: 0,
							width: 1,
							color: '#808080'
						}]
					},
					tooltip: {
						formatter: function() {
				                return '<b>'+ this.series.name +'</b><br/>'+
								this.x +': '+ this.y;
						}
					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -10,
						y: 100,
						borderWidth: 0
					},
					series: seriesContent
				});
				
				
			});
    <%}else{%>
       var t = setTimeout("viewReportSum(<%=id%>);",<%=timeRefresh%>); //10 phut

  // var t = setTimeout("document.reportSystemSum.submit();",30*1000); //2 seconds measured in miliseconds

			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						zoomType: 'x',
						spacingRight: 20
					},
				    title: {
						text: ''
					},
				    subtitle: {
						text: document.ontouchstart === undefined ?
							'' :
							''
					},
					xAxis: {
						type: 'datetime',
						maxZoom:<%=maxZoom%> , // 24 * 3600000 one days
						title: {
							text: null
						}
					},
					yAxis: {
						title: {
							text: 'Total'
						},
						min: 0.6,
						startOnTick: false,
						showFirstLabel: false
					},
					tooltip: {
						shared: true					
					},
					legend: {
						enabled: false
					},
					plotOptions: {
						area: {
							fillColor: {
								linearGradient: [0, 0, 0, 300],
								stops: [
									[0, Highcharts.getOptions().colors[0]],
									[1, 'rgba(2,0,0,0)']
								]
							},
							lineWidth: 1,
							marker: {
								enabled: false,
								states: {
									hover: {
										enabled: true,
										radius: 5
									}
								}
							},
							shadow: false,
							states: {
								hover: {
									lineWidth: 1						
								}
							}
						}
					},
				
					series: seriesContent
				});
				
				
			});
    
    <%}%>
		</script>

		
		<!-- 3. Add the container -->
		<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>