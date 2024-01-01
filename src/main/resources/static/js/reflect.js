function submitReflectForm() {
    var hoTen = document.getElementById('RName').value;
    var noiDung = document.getElementById('reflect-text').value;

    if (!hoTen || !noiDung) {
        alert('Vui lòng nhập đầy đủ thông tin.');
        return;
    }

    axios.post('/reflect/api', {
        hoTen: hoTen,
        noiDungPhanAnh: noiDung,
        trangThai: 'Chưa giải quyết'
    })
    .then(function(response) {
        $('#disablebackdrop').modal('hide');
        updateTableData();
    })
    .catch(function(error) {
        console.error('Error submitting reflect form:', error);
        alert('Đã xảy ra lỗi khi gửi dữ liệu.' + error.message);
    });
}

function updateTableData() {
    location.reload();
}

function handleReflect() {
    document.querySelectorAll('.select-reflect').forEach(function(checkbox) {
        checkbox.style.display = 'inline-block';
    });

    document.querySelector('#handleReflectRow').style.display = 'table-row';

    document.querySelectorAll('.select-reflect').forEach(function(checkbox) {
        var reflectId = checkbox.value;
        var statusElement = document.querySelector('.status-' + reflectId);
        if (statusElement && statusElement.innerText.trim() === 'Đã giải quyết') {
            checkbox.style.display = 'none';
        }
    });
}

function saveSelectedReflects() {
    var selectedReflects = document.querySelectorAll('.select-reflect:checked');

    selectedReflects.forEach(function(checkbox) {
        var reflectId = checkbox.value;

        axios.post('/reflect/api/update-reflects', {
            reflectIds: [reflectId],
            status: 'Đã giải quyết'
        })
        .then(function(response) {
            var statusElement = checkbox.parentElement.parentElement.querySelector('.text-right span');
            if (statusElement) {
                statusElement.innerText = 'Đã giải quyết';
                checkbox.disabled = true;
            }
        })
        .catch(function(error) {
            console.error('Error updating status:', error);
        });
    });

    document.querySelectorAll('.select-reflect').forEach(function(checkbox) {
        checkbox.style.display = 'none';
    });

    document.querySelector('#handleReflectRow').style.display = 'none';
}
