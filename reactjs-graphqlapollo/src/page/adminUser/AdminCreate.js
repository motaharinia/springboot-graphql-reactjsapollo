import React, {useState} from 'react';
import {useMutation} from '@apollo/react-hooks';

import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

import {useStyles} from './AdminStyles'
import {ADMIN_USER_CREATE_MUTATION} from "./AdminQueries";
import FormLabel from "@material-ui/core/FormLabel";
import {Header} from "../../common/header/Header";
import {ResultHandling} from "../../common/ResultHandling";

function CloseButton() {
    window.location.href = "/"
}


export default function AdminCreate() {


    //تعریف متغیر state فرم
    const [formResult, setFormResult] = useState({
        crudType:"CREATE",
        data:"",
        error:""
    });


    //تعریف متغیر استایل
    const classes = useStyles();

    let initialState = {
        username: "",
        firstName: "",
        lastName: "",
        defaultUserAdminContact_address: "",
        defaultAdminUserContact_city_id:1,
        gender_id: ""
    }

    //تعریف متغیر state فرم
    const [formData, setFormData] = useState(initialState);

    //تعریف متغیر ثبت کننده
    const [adminCreate] = useMutation(ADMIN_USER_CREATE_MUTATION);


    //تغییرات فرم را در متغیر state ذخیره میکنیم
    const handleChange = (event) => {
        const inoutName = event.target.name;
        const inputValue = event.target.value;
        if (inoutName === "gender_id") {
            formData[inoutName] = parseInt(inputValue);
        } else {
            formData[inoutName] = inputValue;
        }
        setFormData({
            ...formData
        });
    };

    //متد ثبت کننده اطلاعات طبق داده فرم
    const submitCreate = (event) => {
        adminCreate({variables: formData})
            .then(({data}) => {
                setFormResult({...formResult,"data":data});
            })
            .catch(error => {
                setFormResult({...formResult,"error":error});
            })
        setFormData(formData);
    };

    //نمایش اطلاعات state در فرم
    return (
        <div>
        <Header viewCloseButton={true}   pageTitle="ثبت ادمین جدید" />
            <div className={classes.root}>
                <div>
                    <Grid container spacing={1}>
                        <Grid item xs={4}>
                            <FormLabel component="legend" className={classes.labelRTLStyle}>کلمه کاربری :</FormLabel>
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                required
                                id="username"
                                name="username"
                                label="کلمه کاربری"
                                value={formData.username}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                            />
                        </Grid>
                        <Grid item xs={4}>
                        </Grid>
                    </Grid>
                </div>
                <div>
                    <Grid container spacing={1}>
                        <Grid item xs={4}>
                            <FormLabel component="legend" className={classes.labelRTLStyle}>نام :</FormLabel>
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                required
                                id="firstName"
                                name="firstName"
                                label="نام"
                                value={formData.firstName}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                            />
                        </Grid>
                        <Grid item xs={4}>
                        </Grid>
                    </Grid>
                </div>
                <div>
                    <Grid container spacing={1}>
                        <Grid item xs={4}>
                            <FormLabel component="legend" className={classes.labelRTLStyle}>نام خانوادگی :</FormLabel>
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                required
                                id="lastName"
                                name="lastName"
                                label="نام خانوادگی"
                                value={formData.lastName}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                            />
                        </Grid>
                        <Grid item xs={4}>
                        </Grid>
                    </Grid>
                </div>
                <div>
                    <Grid container spacing={1}>
                        <Grid item xs={4}>
                            <FormLabel component="legend" className={classes.labelRTLStyle}>جنسیت :</FormLabel>
                        </Grid>
                        <Grid item xs={4}>
                            <FormControl variant="outlined" fullWidth>
                                <InputLabel htmlFor="outlined-age-native-simple">جنسیت</InputLabel>
                                <Select
                                    native
                                    value={formData.gender_id}
                                    onChange={handleChange}
                                    label="جنسیت"
                                    inputProps={{
                                        name: 'gender_id',
                                        id: 'outlined-age-native-simple',
                                    }}
                                >
                                    <option aria-label="لطفا یک مورد را انتخاب نمایید" value=""/>
                                    <option value={1}>مرد</option>
                                    <option value={2}>زن</option>
                                </Select>
                            </FormControl>
                        </Grid>
                        <Grid item xs={4}>
                        </Grid>
                    </Grid>
                </div>
                <div>
                    <Grid container spacing={1}>
                        <Grid item xs={4}>
                            <FormLabel component="legend" className={classes.labelRTLStyle}>نشانی :</FormLabel>
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                id="defaultAdminUserContact_address"
                                name="defaultAdminUserContact_address"
                                label="نشانی"
                                value={formData.defaultAdminUserContact_address}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                                multiline
                                rows={5}
                            />
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
                            <Button onClick={submitCreate} type="submit" variant="contained" color="primary">
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

