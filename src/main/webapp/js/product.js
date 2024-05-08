Coldigo.produto = new Object();

$(document).ready(function () {
	alert("Buscando marcas...")
	Coldigo.produto.carregarMarcas = function() {
		$ajax({
			type: "GET",
			url: "/projeto-trilha-web/rest/marca/buscar",
			success: function(marcas) {
				alert("Success!")
			},
			error: function(info){
				alert("Error!")
			}
		})
	}
	Coldigo.produto.carregarMarcas();
})