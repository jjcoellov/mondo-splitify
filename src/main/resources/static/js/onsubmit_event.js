// Below Function Executes On Form Submit
function ValidationEvent() {
// Storing Field Values In Variables
    var friends = document.getElementsByName('friends');
    for (index = 0; index < friends.length; ++index) {
        if (friends[index].checked) {
            return true
        }
    }
    document.getElementById('errorMessage').removeAttribute("class");;
    return false
}
