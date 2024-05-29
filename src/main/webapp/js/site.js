/**
 * 
 */

 function validaFaleConosco() {
  var nome = document.frmfaleconosco.txtnome.value;
  var expRegNome = new RegExp("^[A-zÀ-ú ]{3,}$");
  if (!expRegNome.test(nome)) {
    alert("Nome inválido.");
    document.frmfaleconosco.txtnome.focus();
    return false;
  }

  var fone = document.frmfaleconosco.txtfone.value;
  var expRegFone = new RegExp("^[(]{1}[0-9]{2}[)]{1}[0-9]{4,5}[-][0-9]{4}$");
  if (!expRegFone.test(fone)) {
    alert("Telefone inválido.");
    document.frmfaleconosco.txtfone.focus();
    return false;
  }
  return true;
}

function verificaMotivo(motivo) {
  var element = document.getElementById("opcaoProduto");

  if (motivo == "PR") {
    var select = document.createElement("select");
    select.setAttribute("name", "selproduto");

    var option = document.createElement("option");
    option.setAttribute("value", "");

    var texto = document.createTextNode("Selecione um produto");
    option.appendChild(texto);
    select.appendChild(option);

    var option = document.createElement("option");
    option.setAttribute("value", "FR");
    var texto = document.createTextNode("Freezer");
    option.appendChild(texto);
    select.appendChild(option);

    var option = document.createElement("option");
    option.setAttribute("value", "GE");
    var texto = document.createTextNode("Geladeira");
    option.appendChild(texto);
    select.appendChild(option);

    element.appendChild(select);
  } else {
    if (element.firstChild) {
      element.removeChild(element.firstChild);
    }
  }
}

$(document).ready(function () {
  $("header").load("./pages/site/components/header.html");
  $("nav").load("./pages/site/components/menu.html");
  $("footer").load("./pages/site/components/footer.html");
});
