$(document).ready(function() {
    // 手書きJavaScript
    $('#image1').on('change', function(e) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $("#image1_preview").attr('src', e.target.result);
        }
        reader.readAsDataURL(e.target.files[0]);
    });
});