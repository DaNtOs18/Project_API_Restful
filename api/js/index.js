var url = "http://localhost:8080/";
var navBar = document.getElementById("navbar").classList;
var login = document.getElementById("login").classList;
var errorLogIn = document.getElementById("error_login").classList;
var menu = document.getElementById("menu").classList;
var token;
var user;
var suppliers;  

var hideEverything = function hideEverything() {
    var hideableSections = document.querySelectorAll('.hideable-section');
    var keyArray = Object.keys(hideableSections);
    keyArray.forEach(function(key){
        hideableSections[key].style.display = 'none';
    });
}

var showSectionWithId = function showSectionWithId(id) {
    hideEverything();
    var element = document.getElementById(id); 
    element.style.display = 'block'; 
    menu.add("hidden");
    navBar.remove("hidden");
}

function logIn() {
    let userName = document.getElementById("user").value;
    let password = document.getElementById("password").value;
    var details = {
        'user': userName,
        'password': password,
    };
    
    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    
    fetch(url + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: formBody,
    }).then(res => res.json())
    .then(function(res) {
        user = res;
        token = user.token;
        console.log(res);
        if(res.id === 0) {
            errorLogIn.remove("hidden");
        }
        else {
            login.add("hidden");
            errorLogIn.add("hidden");
            menu.remove("hidden");
        }
    });
}

function getAllItems(idDiv) {   
    let config = {
        headers: {
            Authorization: token
        }
    }
    axios
    .get((url + 'items/'),config)
    .then((response) => {
        items = response.data;
        setCookie('items',items);
        return items;
    })
    .then(() => {
        showSectionWithId(idDiv);
        var vueItems = new Vue({
            el: '#'+idDiv,
            data() {
                return {
                    items: getCookie("items")
                }
            }
        })
    });
}

function getItem(id) { 
    let suppliers = getCookie("suppliers");
    let config = {
        headers: {
            Authorization: token
        }
    }
    axios
    .get((url + 'item/' + id),config)
    .then((response) => {
        item = response.data;
        setCookie('item',item);
    })
    .then(() => {
        var vueItem = new Vue({
            el: '#item_details',
            data() {
                return {
                    item: getCookie("item"),
                    suppliers: suppliers,
                }
            },
            watch: {
                item: function () {
                    console.log("aqui");
                    vueItem.$forceUpdate();
                }
            }
        });
        if(vueItem._uid != 1) {
            console.log(item)
            setTimeout(function() { showSectionWithId("item_details") },100)
        }
        else {
            showSectionWithId("item_details");
        }
    });
}

async function getSupplier(id) { 
    let supplier;
    let config = {
        headers: {
            Authorization: token
        }
    }
    axios
    .get((url + 'supplier/' + id),config)
    .then((response) => {
        supplier = response.data;
        setCookie("supplier",supplier);
    })
    .then(()=> supplier)
}

function getAllSuppliers(idDiv) { 
    let config = {
        headers: {
            Authorization: token
        }
    }
    axios
    .get((url + 'suppliers'),config)
    .then((response) => {
        suppliers = response.data;
        setCookie('suppliers',suppliers);
        return suppliers;
    })
    .then(() => {
        if(idDiv != ""){
            showSectionWithId(idDiv);
            var vueSupp = new Vue({
                el: '#'+idDiv,
                data() {
                    return {
                        suppliers: getCookie("suppliers")
                    }
                }
            })
        }
    });
}

function updateItem() {
    let itemToPut = setItemToUpdate();
    let config = {
        headers: {
            Authorization: token,
        }
    }
    axios
    .put((url + 'item/' + itemToPut.id),itemToPut,config)
    .then((response) => {
        console.log(response);
        return response;
    })
}

function setItemToUpdate() {
    let itemId = document.getElementById("item_id").value;
    let description = document.getElementById("description").value;
    let price = document.getElementById("price").value;
    let pricesReductionId = document.getElementsByName("price_reduction_id");
    let pricesReductionValue = document.getElementsByName("price_reduction_red");
    let pricesReductionStartDate = document.getElementsByName("price_reduction_start");
    let pricesReductionEndDate = document.getElementsByName("price_reduction_end");
    let pricesReduction = [];
    let priceReduction;
    for (let i = 0; i < pricesReductionId.length; i++) {
        priceReduction = {
            "id": pricesReductionId[i].value,
            /*"reducedPrice": pricesReductionValue[i].value,
            "startDate": pricesReductionStartDate[i].value,
            "endDate": pricesReductionEndDate[i].value*/
        }
        pricesReduction.push(priceReduction);
    }
    let supplierId = document.getElementsByName("supplier_id");
    let supplierName = document.getElementsByName("supplier_name");
    let supplierCountry = document.getElementsByName("supplier_country");
    let suppliersItem = [];
    let supplierItem;
    for (let i = 0; i < supplierId.length; i++) {
        supplierItem = {
            "id": supplierId[i].value,
            "name": supplierName[i].value,
            "country": supplierCountry[i].value
        }
        suppliersItem.push(supplierItem);
    }
    let isActive = document.getElementById("state_check").checked;
    let reason = document.getElementById("reason_txt").value;
    if(isActive) reason = "";
    let itemToUpdate = {
        "id": itemId,
        "description": description,
        "price": price,
        "state": {
            "isActive": isActive,
            "reason": reason,
            "changedBy": {
                "id": user.id
            } 
        },
        "pricesReductions": pricesReduction,
        "suppliers": suppliersItem
    }
    console.log(itemToUpdate);
    return itemToUpdate;
}

function createSupplier() {
    let supplierNew = setNewSupplier();
    let config = {
        headers: {
            Authorization: token,
        }
    }
    axios
    .post((url + 'supplier/'),supplierNew,config)
    .then((response) => {
        console.log(response);
        return response;
    })
}

function setNewSupplier() {
    let nameSupplier = document.getElementById("name_supplier").value;
    let countrySupplier = document.getElementById("country_supplier").value;
    let supplierNew = {
        "name": nameSupplier,
        "country": countrySupplier
    }
    return supplierNew;
}

function createPriceReduction() {
    let priceReductionNew = setNewPriceReduction();
    console.log(priceReductionNew);
    let config = {
        headers: {
            Authorization: token,
        }
    }
    axios
    .post((url + 'item/price_reduction/'),priceReductionNew,config)
    .then((response) => {
        console.log(response);
        return response;
    })
}

function setNewPriceReduction() {
    let value = document.getElementById("new_value").value;
    let startDate = document.getElementById("new_start_date").value;
    let endDate = document.getElementById("new_end_date").value;
    let itemRelationated = document.getElementById("item_relationated").value;
    let newPriceReduction = {
        "reducedPrice": value,
        "startDate": startDate,
        "endDate": endDate,
        "itemRelationated": {
            "id": itemRelationated
        }
    }
    return newPriceReduction;
}

function createItem(){
    let ItemNew = setNewItem();
    console.log(ItemNew);
    let config = {
        headers: {
            Authorization: token,
        }
    }
    axios
    .post((url + 'item/'),ItemNew,config)
    .then((response) => {
        console.log(response);
        return response;
    })
}

function setNewItem () {
    let itemCode = document.getElementById("new_item_code").value;
    let description = document.getElementById("new_description").value;
    let price = document.getElementById("new_price").value;
    let supplier = document.getElementById("supplier_new_item").value;
    let state = document.getElementById("state_check").checked;
    let reason = document.getElementById("reason_txt").value;
    let newItem = {
        "itemCode": itemCode,
        "description": description,
        "price": price,
        "suppliers": [
            {
                "id": supplier
            }
        ],
        "state": {
            "isActive": state,
            "reason": reason,
            "changedBy": {
                "id": user.id
            } 
        },
        "createdBy": {
            "id": user.id
        }
    }
    return newItem;
}

function addSupplierRow(id) {
    let supplierSelect = document.getElementById("suppliers_select");
    let text = supplierSelect.options[supplierSelect.selectedIndex].text;
    let name = text.split(" ")[0];
    let country = text.split(" ")[2];
    let supplierSelectValue = supplierSelect.value;
    let supplierList = document.getElementById("supplier_list");
    var newDiv = document.createElement("div");
    newDiv.innerHTML = `
    <div id="supplier` + id + `" class="w-full flex my-1">
    <input type="hidden" name="supplier_id" value="` + id + `">
    <div class="w-1/2 flex">
    <div class="lg:w-1/2 flex flex-col items-center justify-center">
    <input class="w-full text-center" name="supplier_name" value="` + name + `" readonly> 
    </div>
    <div class="lg:w-1/2 flex flex-col items-center justify-center"> 
    <input type="text" class="w-full text-center" name="supplier_country" value="` + country + `" readonly>
    </div>
    </div>
    <button class="w-1/4 inline-block mx-auto bg-red-400 border border-red-600 rounded text-lg font-semibold cursor-pointer hover:bg-red-600 hover:text-white">Remove</button>
    </div>`;
    while(newDiv.firstChild) {
        supplierList.appendChild(newDiv.firstChild);
    }
}

function removePriceReduction(e){
    document.getElementById("price_reduction"+e.value).remove();
}

function handleChange(checkbox) {
    let reason = document.getElementById("reason");
    let reasonNew = document.getElementById("reason_new");
    if(checkbox.checked == true){
        reason.style.display = "none";
        reasonNew.classList.add("hidden");
        reasonNew.classList.remove("flex");
    }else{
        reason.style.display = "flex";
        reasonNew.classList.remove("hidden");
        reasonNew.classList.add("flex");
    }
}

function setCookie(name,value) {
    document.cookie = name + "=" + (JSON.stringify(value) || "") + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    var value;
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) {
            value = JSON.parse(c.substring(nameEQ.length,c.length)); 
            return value;
        }
    }
    return null;
}

const dateFormat = function (str) {
    if (str != null) {
        return moment(str).format("DD MMM YYYY");
    }
    return "";
};

const filters = {
    dateFormat
};

Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));