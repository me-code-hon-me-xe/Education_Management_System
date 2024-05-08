document.addEventListener("DOMContentLoaded", function () {

    var addAdminModal = document.getElementById("add-modal");
    var addBtn = document.getElementById("add-button");
    var addAdminCancel = document.getElementById("cancel-add");

    addBtn.onclick = function () {
        addAdminModal.style.display = "block";
    }


    addAdminCancel.onclick = function () {
        addAdminModal.style.display = "none";
    }
});

function checkEmptyFields(fields, errorMessage) {
    for (let i = 0; i < fields.length; i++) {
        const element = fields[i];
        if (element.tagName === 'INPUT') {
            const inputType = element.getAttribute('type');
            if (inputType === 'text' || inputType === 'date' || inputType === 'tel' || inputType === "email") {
                if (element.value.trim() === '') {
                    errorMessage = "Please fill all fields"
                }
            } else if (inputType === 'number') {
                if (isNaN(element.value)) {
                    errorMessage = "Please fill all fields"
                }
            }
        }
    }
    return errorMessage
}

function checkFieldsValue(fields, errorMessage) {

}

function validateForm() {
    var fields = document.getElementsByClassName("add-input");
    if (fields) {
        console.log(fields)
    } else {console.log("Error")}
    // Clear previous error messages
    document.getElementById("error-message").innerText = "";
    closeErrorPopup(); // Close the error popup

    // Perform validation checks
    var errorMessage = "";

    // check empty fields
    errorMessage = checkEmptyFields(fields, errorMessage)

    if (errorMessage !== "") {
        // Display error messages in the popup
        document.getElementById("error-message").innerText = errorMessage;
        openErrorPopup();
        return false; // Prevent form submission
    }

    return true; // Allow form submission if all checks pass
}

function openErrorPopup() {
    document.getElementById("error-popup").style.display = "block";
}

function closeErrorPopup() {
    document.getElementById("error-popup").style.display = "none";
}