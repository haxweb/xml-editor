import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';

@Injectable()
export class XsdService {
	
	constructor (private http: Http) {}
	
	list() {
		return this.http.get("/xml-editor/api/xsd/list")
			.map(res => {
				return res.json();
		});
	}
	
}