function writeErrors(errors, dest) {
    //errors = res.data.data
    for (let key in errors) {
        //console.log(errors[key]['field'] + "======" + errors[key]['defaultMessage']);
        let k = errors[key]["field"] + "Err";
        let err = dest[k];
        console.log("xxx", dest[k]);
        if (err == undefined || err == "") {
            dest[k] = errors[key]["defaultMessage"];
        } else {
            dest[k] = dest[k] + "," + errors[key]["defaultMessage"];
        }
    }
}
