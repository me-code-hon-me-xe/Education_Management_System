document.addEventListener("DOMContentLoaded", function () {

    var modal = document.getElementsByClassName("modal")[0];

// Get the button that opens the modal
    var btn = document.getElementsByClassName("logo-edit")[0];
    if (modal !== null) {
        // Element with the specified ID was found
        console.log("Element found:", modal);
    } else {
        // Element with the specified ID was not found
        console.log("Element not found");
    }
// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
    btn.onclick = function () {
        console.log("HEllo")
        modal.style.display = "block";
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }
});
