export class Element {

  constructor(
    protected _id?: number
  ){}


	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}
}
