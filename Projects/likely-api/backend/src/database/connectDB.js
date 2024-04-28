import * as mongoose from 'mongoose';
import 'dotenv/config';

const {
    DB_URI_LOCAL,
    DB_URI_PROD,
    DB_URI_PP,
    DB_AUTHSOURCE,
    DB_USERNAME,
    DB_PASSWORD,
    NODE_ENV
} = process.env;

let URI;

if (NODE_ENV === 'local') URI = DB_URI_LOCAL;
if (NODE_ENV === 'preprod') URI = DB_URI_PP;
if (NODE_ENV === 'prod') URI = DB_URI_PROD;

export const connectDB = async () => {
    console.log(URI);
    try {
        await mongoose.connect(URI, {
            authSource: DB_AUTHSOURCE,
            user: DB_USERNAME,
            pass: DB_PASSWORD
        });
    } catch (error) {
        console.error(error);
    }
};
