import React, {useEffect, useState} from 'react';
import {useMutation, useQuery} from '@apollo/react-hooks';

import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import CircularProgress from '@material-ui/core/CircularProgress';
import FormLabel from "@material-ui/core/FormLabel";

import { useStyles } from './AdminStyles'
import {ADMIN_USER_READ_GRID_BY_ID,ADMIN_USER_DELETE_MUTATION} from "./AdminQueries";
import {Header} from "../../common/header/Header";
import {ResultHandling} from "../../common/ResultHandling";


function CloseButton() {
    window.location.href="/"
}

function AdminDelete() {

    //تعریف متغیر استایل
    const classes = useStyles();

    //تعریف متغیر state فرم
    const [formResult, setFormResult] = useState({
        crudType:"DELETE",
        data:"",
        error:""
    });

    let initialState = {
        username: "",
        firstName: "",
        lastName: "",
        gender_id: "",
        defaultUserAdminContact_address: 1
    }

    //تعریف متغیر state فرم
    const [formData, setFormData] = useState(initialState);


    const [adminDelete ] = useMutation(ADMIN_USER_DELETE_MUTATION);


    //متد حذف کننده اطلاعات طبق داده فرم
    const submitDelete = (event) => {
        adminDelete({variables: formData})
            .then(({data}) => {
                setFormResult({...formResult,"data":data});
            })
            .catch(error => {
                setFormResult({...formResult,"error":error});
            });
        setFormData(formData);
    };


    //تعریف کوئری خوانش با شناسه و قراردادن مقدار آن در متغیر داده فرم
    let rowNewId = window.location.pathname.split("/")[2];
    const {loading, error, data} = useQuery(ADMIN_USER_READ_GRID_BY_ID,{
        variables:{id:rowNewId}
    });

    //فراخوانی داده از سرور فقط برای یک بار و جلوگیری از رفرش تو در توی صفحه و ذخیره داده سرور در متغیر  state
    useEffect(() => {
        if (!loading && !error) {
            let readQueryData = data.common_adminUser_readById;
            readQueryData["defaultAdminUserContact_city_id"] = 1;
            readQueryData["gender_id"] = 1;
            setFormData(readQueryData);
        }
    }, [loading, error, data]);

    //در صورت عدم لود داده لودینگ نمایش داده شود
    if (loading === undefined || loading) {
        return (<div><CircularProgress /></div>)
    }
    //در صورت بروز خطا ، پیام آن نمایش داده شود
    if (error) {
        error["crudType"]= "DELETE";
        return (<div>  <ResultHandling result={error}  open={true} key={Math.random()} /></div>)
    }


        return (
            <div>
                <Header viewCloseButton={true}   pageTitle="حذف ادمین" />
                <div className={classes.root}>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelRTLStyle}  >کلمه کاربری :</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelStyle}  >{formData.username}</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelRTLStyle}  >نام :</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelStyle}  >{formData.firstName}</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelRTLStyle}  >نام خانوادگی :</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelStyle}  >{formData.lastName}</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelRTLStyle}  >جنسیت :</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelStyle}  >{formData.gender_langKey}</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelRTLStyle}  >نشانی :</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                                <FormLabel component="legend" className={classes.labelStyle}  >{formData.defaultAdminUserContact_address}</FormLabel>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                    <div>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                            </Grid>
                            <Grid item xs={4}>
                                <Button onClick={submitDelete} type="submit" variant="contained" color="primary">
                                    تایید
                                </Button>
                                <Button onClick={CloseButton} variant="contained" color="secondary"
                                        className={classes.marginButton}>
                                    انصراف
                                </Button>
                            </Grid>
                            <Grid item xs={4}>
                            </Grid>
                        </Grid>
                    </div>
                </div>
                <ResultHandling result={formResult}  open={true} key={Math.random()} />
            </div>
        );

}

export default AdminDelete;