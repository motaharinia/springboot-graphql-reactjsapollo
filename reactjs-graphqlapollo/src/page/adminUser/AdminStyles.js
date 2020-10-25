import {makeStyles} from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
        root: {
            flexGrow: 1,
            fontFamily: "IRANSans !important",
            boxShadow: "0px 0px 3px #aaa",
            padding: "1%",
            '& > *': {
                margin: theme.spacing(1),
            },
        },
        appBar: {
            zIndex: theme.zIndex.drawer + 1,
            position: "relative"
        },
        gridHeight: {
            height: 400
        },
        marginButton: {
            margin: theme.spacing(1),
        },
        closeButton: {
            textAlign: 'center',
            lineHeight: '78px',
        },
        labelStyle: {
            fontFamily: "IRANSans !important",
            color: "black !important",
            fontSize: "14px !important",
            fontWeight: "500 !important",
            lineHeight: "49px !important"
        },
        labelErrorStyle: {
            fontFamily: "IRANSans !important",
            color: "red !important",
            fontSize: "18px !important",
            fontWeight: "500 !important",
            lineHeight: "300px !important",
            textAlign: "center !important"
        },

        labelRTLStyle: {
            fontFamily: "IRANSans !important",
            color: "black !important",
            fontSize: "14px !important",
            fontWeight: "500 !important",
            textAlign: "left !important",
            lineHeight: "49px !important"
        }

    }));


export {useStyles};