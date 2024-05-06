Coldigo.produto = new Object();

$(document).ready(function () {
	Coldigo.produto.carregarMarcas = function() {
		$ajax({
			type: "GET",
			url: "/projeto-trilha-web/rest/marca/buscar",
			success: function() {
			},
			error: function(){
			}
		})
	}
})