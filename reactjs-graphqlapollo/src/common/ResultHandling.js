import React from 'react';

import Modal from '@material-ui/core/Modal';
import CloseIcon from "@material-ui/icons/Close";

import {useStyles} from './Styles'


function ResultHandling(props) {
    //تعریف متغیر استایل
    const classes = useStyles();
    //تعریف متغیر state نمایش دادن یا ندادن پاپ آپ
    const [open, setOpen] = React.useState(props.open);
    let flagOpen = false;
    const handleClose = () => {
        if(flagOpen){
            window.location.href = "/"
        }else{
            setOpen(flagOpen);
        }

    };
    // نوع عملیات
    let operation = "";
    // eslint-disable-next-line default-case
    switch (props.result.crudType) {
        case "CREATE":
            operation = "ثبت";
            break;
        case "UPDATE":
            operation = "ویرایش";
            break;
        case "DELETE":
            operation = "حذف";
            break;
    }
    // استایل پیام ها براساس نوع آن
    let messageStyle = classes.modalBodyDefault;
    // عنوان پیام
    let titleModal = "پیام";
    // پیام
    let message = "";
     // بررسی پر بودن خطا ها
    if (props.result.error === "") {
        // بررسی پر بودن داده ها
        if (props.result.data !== "") {
            messageStyle = classes.modalBodySuccess;
            message = `${operation}    با موفقیت انجام شد`;
            flagOpen = true;
        }
    } else {
        messageStyle = classes.modalBodyError;
        titleModal = `${operation}   با خطای زیر مواجه شد`;
        // اطلاعات خطا ها
        let dataError = props.result;
        flagOpen = true;
        if (props.result.error !== undefined) {
             dataError = props.result.error;
            flagOpen = false;
        }
        // بررسی پر بودن graphQLErrors
        if (dataError.graphQLErrors !== undefined && dataError.graphQLErrors !== null) {
            message = `پیام خطا : ${dataError.graphQLErrors[0].message}`;
        }
        // بررسی پر بودن networkError
        if (dataError.networkError !== undefined && dataError.networkError !== null) {
            message = `پیام خطا : ${dataError.networkError}`;
            flagOpen = true;
        }
    }

    if (message === "") {
        return (<div> </div>)
    }
    // پا آپ پیام
    let body = (<div className={messageStyle}>
        <CloseIcon onClick={handleClose} className={classes.closeButtonError}/>
        <h3 id="simple-modal-title">{titleModal}</h3>
        <p id="simple-modal-description">
            {message}
        </p>
    </div>);


    return (
        <div>
            <Modal
                className={classes.modal}
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                {body}
            </Modal>
        </div>
    );

}

export {ResultHandling}