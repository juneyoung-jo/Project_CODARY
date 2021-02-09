import axios from "axios";
import { API_BASE_URL } from "../config";

function createInstance() {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        header: {
            'Content-Type': 'application/json'
        }
    });
    return instance;
}

function createInstanceFile() {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        header: {
            'Content-Type': 'multipart/form-data'
        }
    });
    return instance;
}

export { createInstance,createInstanceFile };


