
window.onload = () => {
    // 1. what is API
// 2. How do I call API
// 3. Explain code
    const host = "https://provinces.open-api.vn/api/";
    var callAPI = (api) => {
        return axios.get(api)
            .then((response) => {
                renderData(response.data, "province");
            });
    }
    callAPI('https://provinces.open-api.vn/api/?depth=1');
    var callApiDistrict = (api) => {
        return axios.get(api)
            .then((response) => {
                renderData(response.data.districts, "district");
            });
    }
    var callApiWard = (api) => {
        return axios.get(api)
            .then((response) => {
                console.log(response);
                renderData(response.data.wards, "ward");
            });
    }

    var renderData = (array, select) => {
        let row = ' <option disable value="">Chọn</option>';
        array.forEach(element => {
            row += `<option value="${element.code}-${element.name}" >${element.name}</option>`
        });
        document.querySelector("#" + select).innerHTML = row
    }

    $("#province").change(() => {
        var split = $("#province").val().split('-');
        console.log(split);
        console.log($("#province").val());
        callApiDistrict(host + "p/" + split[0] + "?depth=2");
        printResult();
    });
    $("#district").change(() => {
        var split = $("#district").val().split('-');
        callApiWard(host + "d/" + split[0] + "?depth=2");
        printResult();
    });
    $("#ward").change(() => {
        printResult();
    })

    var printResult = () => {
        if ($("#district").val() != "" && $("#province").val() != "" &&
            $("#ward").val() != "") {
            let result = $("#province option:selected").text() +
                " | " + $("#district option:selected").text() + " | " +
                $("#ward option:selected").text();
            $("#result").text(result)
        }

    }

    $("#close").click(()=>{
        $("#filter").css("display", "none")
    })
    $("#open-filter").click(()=>{
        $("#filter").css("display", "flex")
    })
    $("#close2").click(()=>{
        $("#form-add").css("display", "none")
    })
    $("#open-form-add").click(()=>{
        $("#form-add").css("display", "block")
    })
}

